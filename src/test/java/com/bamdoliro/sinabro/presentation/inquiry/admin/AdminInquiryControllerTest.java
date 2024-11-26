package com.bamdoliro.sinabro.presentation.inquiry.admin;

import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.domain.inquiry.exception.InquiryNotFoundException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.inquiry.admin.dto.request.UpdateInquiryStatusRequest;
import com.bamdoliro.sinabro.presentation.inquiry.admin.dto.response.InquiryAdminResponse;
import com.bamdoliro.sinabro.presentation.inquiry.admin.dto.response.ListInquiryAdminResponse;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.fixture.InquiryFixture;
import com.bamdoliro.sinabro.shared.fixture.UserFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminInquiryControllerTest extends RestDocsTestSupport {

    @Test
    void 어드민이_문의를_전체_조회한다() throws Exception {
        User user = UserFixture.createAdmin();
        List<ListInquiryAdminResponse> responseList = List.of(
                InquiryFixture.createListInquiryAdminResponse(),
                InquiryFixture.createListInquiryAdminResponse(),
                InquiryFixture.createListInquiryAdminResponse()
        );

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getAllInquiriesAdminUseCase.execute(InquiryStatus.WAITING)).willReturn(responseList);

        mockMvc.perform(get("/admin/inquiries")
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

        verify(getAllInquiriesAdminUseCase, times(1)).execute(InquiryStatus.WAITING);
    }

    @Test
    void 어드민이_문의를_상세_조회한다() throws Exception {
        Long inquiryId = 1L;
        User user = UserFixture.createAdmin();
        InquiryAdminResponse response = InquiryFixture.createInquiryAdminResponse();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getInquiryAdminUseCase.execute(inquiryId)).willReturn(response);

        mockMvc.perform(get("/admin/inquiries/{inquiry-id}", inquiryId)
                        .header(org.apache.http.HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(org.apache.http.HttpHeaders.AUTHORIZATION)
                                        .description("Bearer Token")
                        ),
                        pathParameters(
                                parameterWithName("inquiry-id")
                                        .description("조회할 문의의 id")
                        )
                ));

        verify(getInquiryAdminUseCase, times(1)).execute(inquiryId);
    }

    @Test
    void 문의를_상세_조회할_때_문의가_없으면_예외가_발생한다() throws Exception {
        User user = UserFixture.createAdmin();
        Long inquiryId = 1L;

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getInquiryAdminUseCase.execute(inquiryId)).willThrow(new InquiryNotFoundException());

        mockMvc.perform(get("/admin/inquiries/{inquiry-id}", inquiryId)
                        .header(org.apache.http.HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());

        verify(getInquiryAdminUseCase, times(1)).execute(inquiryId);
    }

    @Test
    void 어드민이_문의의_상태를_변경한다() throws Exception {
        User user = UserFixture.createAdmin();
        Long inquiryId = 1L;
        UpdateInquiryStatusRequest request = InquiryFixture.createUpdateInquiryStatusRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(updateInquiryStatusUseCase).execute(anyLong(), any(UpdateInquiryStatusRequest.class));

        mockMvc.perform(patch("/admin/inquiries/{inquiry-id}", inquiryId)
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
                                        .description("상태를 수정할 문의의 id")
                        ),
                        requestFields(
                                fieldWithPath("status")
                                        .description("<<inquiryStatus, 문의 상태(null일 경우 전체 조회)>>")
                        )
                ));

        verify(updateInquiryStatusUseCase, times(1)).execute(anyLong(), any(UpdateInquiryStatusRequest.class));
    }
}