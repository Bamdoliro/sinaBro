package com.bamdoliro.sinabro.presentation.inquiry.user;

import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.domain.inquiry.exception.InquiryNotFoundException;
import com.bamdoliro.sinabro.domain.inquiry.exception.InvalidInquiryStateException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.request.InquiryRequest;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.response.InquiryUserResponse;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.response.ListInquiryUserResponse;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.fixture.InquiryFixture;
import com.bamdoliro.sinabro.shared.fixture.SharedFixture;
import com.bamdoliro.sinabro.shared.fixture.UserFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserInquiryControllerTest extends RestDocsTestSupport {

    @Test
    void 문의를_생성한다() throws Exception {
        User user = UserFixture.createUser();
        InquiryRequest request = InquiryFixture.createInquiryRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(createInquiryUseCase.execute(any(User.class), any(InquiryRequest.class))).willReturn(SharedFixture.createIdResponse());

        mockMvc.perform(post("/user/inquiries")
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
                                        .description("문의 제목(최대 64글자)"),
                                fieldWithPath("content")
                                        .description("문의 내용(최대 3000글자)")
                        )
                ));

        verify(createInquiryUseCase, times(1)).execute(any(User.class), any(InquiryRequest.class));
    }

    @Test
    void 자신이_작성한_문의를_전체_조회한다() throws Exception {
        User user = UserFixture.createUser();
        List<ListInquiryUserResponse> responseList = List.of(
                InquiryFixture.createListInquiryUserResponse(),
                InquiryFixture.createListInquiryUserResponse(),
                InquiryFixture.createListInquiryUserResponse()
        );

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getAllInquiriesUserUseCase.execute(user, InquiryStatus.WAITING)).willReturn(responseList);

        mockMvc.perform(get("/user/inquiries")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .param("status", InquiryStatus.WAITING.name())
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer Token")
                        ),
                        queryParameters(
                                parameterWithName("status")
                                        .description("<<inquiryStatus, 문의 상태(null일 경우 전체 조회)>>")
                                        .optional()
                        )
                ));

        verify(getAllInquiriesUserUseCase, times(1)).execute(user, InquiryStatus.WAITING);
    }

    @Test
    void 자신이_작성한_문의를_상세_조회한다() throws Exception {
        Long inquiryId = 1L;
        User user = UserFixture.createUser();
        InquiryUserResponse response = InquiryFixture.createInquiryUserResponse();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getInquiryUserUseCase.execute(user, inquiryId)).willReturn(response);

        mockMvc.perform(get("/user/inquiries/{inquiry-id}", inquiryId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer Token")
                        ),
                        pathParameters(
                                parameterWithName("inquiry-id")
                                        .description("조회할 문의의 id")
                        )
                ));

        verify(getInquiryUserUseCase, times(1)).execute(user, inquiryId);
    }

    @Test
    void 문의를_상세_조회할_때_문의가_없으면_예외가_발생한다() throws Exception {
        User user = UserFixture.createUser();
        Long inquiryId = 1L;

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getInquiryUserUseCase.execute(user, inquiryId)).willThrow(new InquiryNotFoundException());

        mockMvc.perform(get("/user/inquiries/{inquiry-id}", inquiryId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());

        verify(getInquiryUserUseCase, times(1)).execute(user, inquiryId);
    }

    @Test
    void 자신이_작성한_문의를_수정한다() throws Exception {
        User user = UserFixture.createUser();
        Long inquiryId = 1L;
        InquiryRequest request = InquiryFixture.createInquiryRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(updateInquiryUseCase).execute(any(User.class), anyLong(), any(InquiryRequest.class));

        mockMvc.perform(put("/user/inquiries/{inquiry-id}", inquiryId)
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
                                parameterWithName("inquiry-id")
                                        .description("수정할 문의의 id")
                        ),
                        requestFields(
                                fieldWithPath("title")
                                        .description("문의 제목(최대 64글자)"),
                                fieldWithPath("content")
                                        .description("문의 내용(최대 3000글자)")
                        )
                ));

        verify(updateInquiryUseCase, times(1)).execute(any(User.class), anyLong(), any(InquiryRequest.class));
    }

    @Test
    void 문의를_수정할_때_문의가_없으면_예외가_발생한다() throws Exception {
        User user = UserFixture.createUser();
        Long inquiryId = 1L;
        InquiryRequest request = InquiryFixture.createInquiryRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new InquiryNotFoundException()).given(updateInquiryUseCase).execute(any(User.class), anyLong(), any(InquiryRequest.class));

        mockMvc.perform(put("/user/inquiries/{inquiry-id}", inquiryId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());

        verify(updateInquiryUseCase, times(1)).execute(any(User.class), anyLong(), any(InquiryRequest.class));
    }

    @Test
    void 문의를_수정할_때_문의의_상태가_진행중이거나_완료라면_예외가_발생한다() throws Exception {
        User user = UserFixture.createUser();
        Long inquiryId = 1L;
        InquiryRequest request = InquiryFixture.createInquiryRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new InvalidInquiryStateException()).given(updateInquiryUseCase).execute(any(User.class), anyLong(), any(InquiryRequest.class));

        mockMvc.perform(put("/user/inquiries/{inquiry-id}", inquiryId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isConflict())

                .andDo(restDocs.document());

        verify(updateInquiryUseCase, times(1)).execute(any(User.class), anyLong(), any(InquiryRequest.class));
    }

    @Test
    void 자신이_작성한_문의를_삭제한다() throws Exception {
        User user = UserFixture.createUser();
        Long inquiryId = 1L;

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(deleteInquiryUseCase).execute(user, inquiryId);

        mockMvc.perform(delete("/user/inquiries/{inquiry-id}", inquiryId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNoContent())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer Token")
                        ),
                        pathParameters(
                                parameterWithName("inquiry-id")
                                        .description("삭제할 문의의 id")
                        )
                ));

        verify(deleteInquiryUseCase, times(1)).execute(user, inquiryId);
    }

    @Test
    void 문의를_삭제할_때_문의가_없으면_예외가_발생한다() throws Exception {
        User user = UserFixture.createUser();
        Long inquiryId = 1L;

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new InquiryNotFoundException()).given(deleteInquiryUseCase).execute(user, inquiryId);

        mockMvc.perform(delete("/user/inquiries/{inquiry-id}", inquiryId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());

        verify(deleteInquiryUseCase, times(1)).execute(user, inquiryId);
    }

    @Test
    void 문의를_삭제할_때_문의의_상태가_진행중이거나_완료라면_예외가_발생한다() throws Exception {
        User user = UserFixture.createUser();
        Long inquiryId = 1L;

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new InvalidInquiryStateException()).given(deleteInquiryUseCase).execute(user, inquiryId);

        mockMvc.perform(delete("/user/inquiries/{inquiry-id}", inquiryId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isConflict())

                .andDo(restDocs.document());

        verify(deleteInquiryUseCase, times(1)).execute(user, inquiryId);
    }
}