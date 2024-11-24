package com.bamdoliro.sinabro.presentation.question;

import com.bamdoliro.sinabro.domain.question.exception.QuestionNotFoundException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.diary.dto.request.DiaryRequest;
import com.bamdoliro.sinabro.presentation.question.dto.request.QuestionRequest;
import com.bamdoliro.sinabro.presentation.question.dto.response.ListQuestionResponse;
import com.bamdoliro.sinabro.presentation.question.dto.response.QuestionResponse;
import com.bamdoliro.sinabro.shared.fixture.*;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuestionControllerTest extends RestDocsTestSupport {

    @Test
    void 자주묻는질문을_생성한다() throws Exception {
        User user = UserFixture.createAdmin();
        QuestionRequest request = QuestionFixture.createQuestionRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(createQuestionUseCase.execute(any(QuestionRequest.class))).willReturn(SharedFixture.createIdResponse());

        mockMvc.perform(post("/questions")
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
                        requestFields(
                                fieldWithPath("title")
                                        .type(JsonFieldType.STRING)
                                        .description("제목(최대 64자)"),
                                fieldWithPath("content")
                                        .type(JsonFieldType.STRING)
                                        .description("내용(최대 1024자")
                        )
                ));

        verify(createQuestionUseCase, times(1)).execute(any(QuestionRequest.class));
    }

    @Test
    void 자주묻는질문을_전체_조회한다() throws Exception {
        List<ListQuestionResponse> responseList = List.of(
                QuestionFixture.createListQuestionResponse(),
                QuestionFixture.createListQuestionResponse(),
                QuestionFixture.createListQuestionResponse()
        );

        given(getAllQuestionUseCase.execute()).willReturn(responseList);

        mockMvc.perform(get("/questions")
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())

                .andDo(restDocs.document());

        verify(getAllQuestionUseCase, times(1)).execute();
    }

    @Test
    void 자주묻는질문을_조회한다() throws Exception {
        Long id = 1L;
        QuestionResponse response = QuestionFixture.createQuestionResponse();

        given(getQuestionUseCase.execute(anyLong())).willReturn(response);

        mockMvc.perform(get("/questions/{question-id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        pathParameters(
                                parameterWithName("question-id")
                                        .description("조회할 자주묻는질문의 id")
                        )
                ));

        verify(getQuestionUseCase, times(1)).execute(anyLong());
    }

    @Test
    void 자주묻는질문을_조회할_때_자주묻는질문이_없으면_예외가_발생한다() throws Exception {
        Long id = 1L;
        given(getQuestionUseCase.execute(anyLong())).willThrow(new QuestionNotFoundException());

        mockMvc.perform(get("/questions/{question-id}", id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());

        verify(getQuestionUseCase, times(1)).execute(anyLong());
    }

    @Test
    void 자주묻는질문을_수정한다() throws Exception {
        Long id = 1L;
        User user = UserFixture.createAdmin();
        QuestionRequest request = QuestionFixture.createQuestionRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(updateQuestionUseCase).execute(anyLong(), any(QuestionRequest.class));

        mockMvc.perform(put("/questions/{question-id}", id)
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
                                parameterWithName("question-id")
                                        .description("수정할 자주묻는질문 id")
                        ),
                        requestFields(
                                fieldWithPath("title")
                                        .type(JsonFieldType.STRING)
                                        .description("64글자 이내의 제목"),
                                fieldWithPath("content")
                                        .type(JsonFieldType.STRING)
                                        .description("1024글자 이내의 내용")
                        )
                ));

        verify(updateQuestionUseCase, times(1)).execute(anyLong(), any(QuestionRequest.class));
    }

    @Test
    void 자주묻는질문을_수정할_때_자주묻는질문이_없으면_에러가_발생한다() throws Exception {
        Long id = 1L;
        User user = UserFixture.createAdmin();
        QuestionRequest request = QuestionFixture.createQuestionRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new QuestionNotFoundException()).given(updateQuestionUseCase).execute(anyLong(), any(QuestionRequest.class));

        mockMvc.perform(put("/questions/{question-id}", id)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request)))

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());
    }

    @Test
    void 자주묻는질문을_삭제한다() throws Exception {
        Long id = 1L;
        User user = UserFixture.createAdmin();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(deleteQuestionUseCase).execute(id);

        mockMvc.perform(delete("/questions/{question-id}", id)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNoContent())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer token")
                        ),
                        pathParameters(
                                parameterWithName("question-id")
                                        .description("삭제할 자주묻는질문 id")
                        )
                ));

        verify(deleteQuestionUseCase, times(1)).execute(id);
    }

    @Test
    void 자주묻는질문을_삭제할_때_자주묻는질문이_없으면_에러가_발생한다() throws Exception {
        Long id = 1L;
        User user = UserFixture.createAdmin();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new QuestionNotFoundException()).given(deleteQuestionUseCase).execute(anyLong());

        mockMvc.perform(delete("/questions/{question-id}", id)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());
    }
}
