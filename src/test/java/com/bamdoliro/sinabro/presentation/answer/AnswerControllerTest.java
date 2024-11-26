package com.bamdoliro.sinabro.presentation.answer;

import com.bamdoliro.sinabro.domain.answer.exception.AnswerNotFoundException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.answer.dto.request.AnswerRequest;
import com.bamdoliro.sinabro.shared.fixture.AnswerFixture;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.fixture.SharedFixture;
import com.bamdoliro.sinabro.shared.fixture.UserFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AnswerControllerTest extends RestDocsTestSupport {

    @Test
    void 답변을_생성한다() throws Exception {
        User user = UserFixture.createAdmin();
        Long inquiryId = 1L;
        AnswerRequest request = AnswerFixture.createAnswerRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(createAnswerUseCase.execute(any(User.class), anyLong(), any(AnswerRequest.class))).willReturn(SharedFixture.createIdResponse());

        mockMvc.perform(post("/answers/{inquiry-id}", inquiryId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isCreated())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer Token")
                        ),
                        pathParameters(
                                parameterWithName("inquiry-id")
                                        .description("답변을 달 문의의 id")
                        ),
                        requestFields(
                                fieldWithPath("content")
                                        .description("답변 내용(최대 3000글자)")
                        )
                ));

        verify(createAnswerUseCase, times(1)).execute(any(User.class), anyLong(), any(AnswerRequest.class));
    }

    @Test
    void 답변을_수정한다() throws Exception {
        User user = UserFixture.createAdmin();
        Long answerId = 1L;
        AnswerRequest request = AnswerFixture.createAnswerRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(updateAnswerUseCase).execute(any(User.class), anyLong(), any(AnswerRequest.class));

        mockMvc.perform(put("/answers/{answer-id}", answerId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isNoContent())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer Token")
                        ),
                        pathParameters(
                                parameterWithName("answer-id")
                                        .description("수정할 답변의 id")
                        ),
                        requestFields(
                                fieldWithPath("content")
                                        .description("수정할 답변 내용(최대 3000글자)")
                        )
                ));

        verify(updateAnswerUseCase, times(1)).execute(any(User.class), anyLong(), any(AnswerRequest.class));
    }

    @Test
    void 답변을_수정할_때_답변이_없으면_예외가_발생한다() throws Exception {
        User user = UserFixture.createAdmin();
        Long answerId = 1L;
        AnswerRequest request = AnswerFixture.createAnswerRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new AnswerNotFoundException()).given(updateAnswerUseCase).execute(any(User.class), anyLong(), any(AnswerRequest.class));

        mockMvc.perform(put("/answers/{answer-id}", answerId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());

        verify(updateAnswerUseCase, times(1)).execute(any(User.class), anyLong(), any(AnswerRequest.class));
    }

    @Test
    void 답변을_삭제한다() throws Exception {
        User user = UserFixture.createAdmin();
        Long answerId = 1L;

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(deleteAnswerUseCase).execute(user, answerId);

        mockMvc.perform(delete("/answers/{answer-id}", answerId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                )

                .andExpect(status().isNoContent())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer Token")
                        ),
                        pathParameters(
                                parameterWithName("answer-id")
                                        .description("삭제할 답변의 id")
                        )
                ));

        verify(deleteAnswerUseCase, times(1)).execute(any(User.class), anyLong());
    }

    @Test
    void 답변을_삭제할_때_답변이_없으면_예외가_발생한다() throws Exception {
        User user = UserFixture.createAdmin();
        Long answerId = 1L;

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new AnswerNotFoundException()).given(deleteAnswerUseCase).execute(any(User.class), anyLong());

        mockMvc.perform(delete("/answers/{answer-id}", answerId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());

        verify(deleteAnswerUseCase, times(1)).execute(any(User.class), anyLong());
    }
}