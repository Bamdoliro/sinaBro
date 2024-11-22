package com.bamdoliro.sinabro.presentation.fcm.token;

import com.bamdoliro.sinabro.domain.fcm.token.exception.FCMTokenAlreadySavedException;
import com.bamdoliro.sinabro.domain.fcm.token.exception.FCMTokenNotFoundException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.fcm.token.dto.request.DeleteFCMTokenRequest;
import com.bamdoliro.sinabro.presentation.fcm.token.dto.request.SaveFCMTokenRequest;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.fixture.UserFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FCMTokenControllerTest extends RestDocsTestSupport {

    @Test
    void FCM_토큰을_저장한다() throws Exception {
        User user = UserFixture.createUser();
        SaveFCMTokenRequest request = new SaveFCMTokenRequest("fcm-token");

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);

        mockMvc.perform(post("/fcm-token")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isCreated())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer token")
                        ),
                        requestFields(
                                fieldWithPath("token")
                                        .description("fcm token")
                        )
                ));

        verify(saveFCMTokenUseCase, times(1)).execute(any(User.class), any(SaveFCMTokenRequest.class));
    }

    @Test
    void 해당_유저가_해당_FCM_토큰을_저장할때_이미_FCM_토큰을_가지고_있다면_에러가_발생한다() throws Exception {
        User user = UserFixture.createUser();
        SaveFCMTokenRequest request = new SaveFCMTokenRequest("fcm-token");

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new FCMTokenAlreadySavedException()).given(saveFCMTokenUseCase).execute(any(User.class), any(SaveFCMTokenRequest.class));

        mockMvc.perform(post("/fcm-token")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isConflict())

                .andDo(restDocs.document());

        verify(saveFCMTokenUseCase, times(1)).execute(any(User.class), any(SaveFCMTokenRequest.class));
    }

    @Test
    void FCM_토큰을_삭제한다() throws Exception {
        User user = UserFixture.createUser();
        DeleteFCMTokenRequest request = new DeleteFCMTokenRequest("fcm-token");

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(deleteFCMTokenUseCase).execute(user, request);

        mockMvc.perform(delete("/fcm-token")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isNoContent())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer token")
                        ),
                        requestFields(
                                fieldWithPath("token")
                                        .description("fcm token")
                        )
                ));

        verify(deleteFCMTokenUseCase, times(1)).execute(any(User.class), any(DeleteFCMTokenRequest.class));
    }

    @Test
    void 해당_유저가_해당_FCM_토큰을_가지고있지_않다면_에러가_발생한다() throws Exception {
        User user = UserFixture.createUser();
        DeleteFCMTokenRequest request = new DeleteFCMTokenRequest("fcm-token");

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new FCMTokenNotFoundException()).given(deleteFCMTokenUseCase).execute(any(User.class), any(DeleteFCMTokenRequest.class));

        mockMvc.perform(delete("/fcm-token")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());

        verify(deleteFCMTokenUseCase, times(1)).execute(any(User.class), any(DeleteFCMTokenRequest.class));
    }
}
