package com.bamdoliro.sinabro.presentation.notifcation;

import com.bamdoliro.sinabro.domain.fcm.token.exception.FCMTokenNotFoundException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.fcm.exception.FailedToSendException;
import com.bamdoliro.sinabro.presentation.notification.dto.request.SendNotificationRequest;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.fixture.UserFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotificationControllerTest extends RestDocsTestSupport {

    @Test
    void 유저에게_알림을_발송한다() throws Exception {
        User user = UserFixture.createUser();
        SendNotificationRequest request = new SendNotificationRequest("당신에게 편지가 왔어요.", "편지가 왔어요 어서 확인해보세요!");

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(sendNotificationUseCase).execute(any(User.class), any(SendNotificationRequest.class));


        mockMvc.perform(post("/notifications")
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
                                fieldWithPath("title")
                                        .description("알림 제목"),
                                fieldWithPath("body")
                                        .description("알림 내용")
                        )
                ));

        verify(sendNotificationUseCase, times(1)).execute(any(User.class), any(SendNotificationRequest.class));
    }

    @Test
    void 유저에_해당하는_FCM_토큰이_없다면_에러가_발생한다() throws Exception {
        User user = UserFixture.createUser();
        SendNotificationRequest request = new SendNotificationRequest("당신에게 편지가 왔어요.", "편지가 왔어요 어서 확인해보세요!");

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new FCMTokenNotFoundException()).given(sendNotificationUseCase).execute(any(User.class), any(SendNotificationRequest.class));

        mockMvc.perform(post("/notifications")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )
                .andExpect(status().isNotFound())

                .andDo(restDocs.document());

        verify(sendNotificationUseCase, times(1)).execute(any(User.class), any(SendNotificationRequest.class));
    }

    @Test
    void 모종의_이유로_FCM_알림_발송과정에서_문제가_발생하면_에러가_발생한다() throws Exception {
        User user = UserFixture.createUser();
        SendNotificationRequest request = new SendNotificationRequest("당신에게 편지가 왔어요.", "편지가 왔어요 어서 확인해보세요!");

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new FailedToSendException()).given(sendNotificationUseCase).execute(any(User.class), any(SendNotificationRequest.class));

        mockMvc.perform(post("/notifications")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )
                .andExpect(status().isInternalServerError())

                .andDo(restDocs.document());

        verify(sendNotificationUseCase, times(1)).execute(any(User.class), any(SendNotificationRequest.class));
    }
}
