package com.bamdoliro.sinabro.presentation.diary;


import com.bamdoliro.sinabro.domain.auth.exception.AuthorityMismatchException;
import com.bamdoliro.sinabro.domain.diary.exception.DiaryAlreadyWrittenException;
import com.bamdoliro.sinabro.domain.diary.exception.DiaryNotFoundException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.diary.dto.request.DiaryRequest;
import com.bamdoliro.sinabro.presentation.diary.dto.request.UpdateDiaryRequest;
import com.bamdoliro.sinabro.presentation.diary.dto.response.DiaryResponse;
import com.bamdoliro.sinabro.presentation.diary.dto.response.ListDiaryResponse;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.fixture.DiaryFixture;
import com.bamdoliro.sinabro.shared.fixture.SharedFixture;
import com.bamdoliro.sinabro.shared.fixture.UserFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDate;
import java.util.List;

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
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DiaryControllerTest extends RestDocsTestSupport {

    @Test
    void 감정일기를_작성한다() throws Exception {
        User user = UserFixture.createUser();
        DiaryRequest request = DiaryFixture.createDiaryRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(createDiaryUseCase.execute(any(User.class), any(DiaryRequest.class))).willReturn(SharedFixture.createIdResponse());

        mockMvc.perform(post("/diaries")
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
                                fieldWithPath("content")
                                        .type(JsonFieldType.STRING)
                                        .description("감정일기 내용(최대 5000자)"),
                                fieldWithPath("emotionList")
                                        .type(JsonFieldType.ARRAY)
                                        .description("<<emotion, 감정(1~3개)>>"),
                                fieldWithPath("writtenAt")
                                        .type(JsonFieldType.STRING)
                                        .description("감정일기 작성일(ISO 8601 형식)")
                        )
                ));

        verify(createDiaryUseCase, times(1)).execute(any(User.class), any(DiaryRequest.class));
    }

    @Test
    void 감정일기를_작성할_때_이미_그날_작성한_감정일기가_있으면_예외가_발생한다() throws Exception {
        User user = UserFixture.createUser();
        DiaryRequest request = DiaryFixture.createDiaryRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(createDiaryUseCase.execute(any(User.class), any(DiaryRequest.class))).willThrow(new DiaryAlreadyWrittenException());

        mockMvc.perform(post("/diaries")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isConflict())

                .andDo(restDocs.document());
    }

    @Test
    void 자신이_작성한_감정일기를_전체_조회한다() throws Exception {
        User user = UserFixture.createUser();
        List<ListDiaryResponse> responseList = List.of(
                DiaryFixture.createListDiaryResponse(),
                DiaryFixture.createListDiaryResponse(),
                DiaryFixture.createListDiaryResponse(),
                DiaryFixture.createListDiaryResponse(),
                DiaryFixture.createListDiaryResponse()
        );

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getAllDiaryUseCase.execute(any(User.class), any(LocalDate.class), any(LocalDate.class))).willReturn(responseList);

        mockMvc.perform(get("/diaries")
                .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                .param("startDate", LocalDate.of(2024, 1, 1).toString())
                .param("endDate", LocalDate.of(2024, 12, 31).toString())
                .contentType(MediaType.APPLICATION_JSON)
        )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        requestHeaders(
                            headerWithName(HttpHeaders.AUTHORIZATION)
                                    .description("Bearer Token")
                        ),
                        queryParameters(
                                parameterWithName("startDate")
                                        .description("조회를 시작할 기준 날짜(ISO 8601 형식)")
                                        .optional(),
                                parameterWithName("endDate")
                                        .description("조회를 종료할 기준 날짜(ISO 8601 형식)")
                                        .optional()
                        )
                ));

        verify(getAllDiaryUseCase, times(1)).execute(any(User.class), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void 자신이_작성한_감정일기를_조회한다() throws Exception {
        Long diaryId = 1L;
        User user = UserFixture.createUser();
        DiaryResponse response = DiaryFixture.createDiaryResponse();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getDiaryUseCase.execute(user, diaryId)).willReturn(response);

        mockMvc.perform(get("/diaries/{diary-id}", diaryId)
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
                                parameterWithName("diary-id")
                                        .description("조회할 감정일기의 id")
                        )
                ));

        verify(getDiaryUseCase, times(1)).execute(user, diaryId);
    }

    @Test
    void 감정일기를_조회할_때_감정일기가_없으면_예외가_발생한다() throws Exception {
        Long diaryId = 1L;
        User user = UserFixture.createUser();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getDiaryUseCase.execute(user, diaryId)).willThrow(new DiaryNotFoundException());

        mockMvc.perform(get("/diaries/{diary-id}", diaryId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());
    }

    @Test
    void 자신이_작성한_감정일기를_수정한다() throws Exception {
        Long diaryId = 1L;
        User user = UserFixture.createUser();
        UpdateDiaryRequest request = DiaryFixture.createUpdateDiaryRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(updateDiaryUseCase).execute(user, diaryId, request);

        mockMvc.perform(put("/diaries/{diary-id}", diaryId)
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
                        requestFields(
                                fieldWithPath("content")
                                        .type(JsonFieldType.STRING)
                                        .description("감정일기 내용(최대 5000자)"),
                                fieldWithPath("emotionList")
                                        .type(JsonFieldType.ARRAY)
                                        .description("<<emotion, 감정(1~3개)>>")
                        )
                ));

        verify(updateDiaryUseCase, times(1)).execute(any(User.class), anyLong(), any(UpdateDiaryRequest.class));
    }

    @Test
    void 감정일기를_수정할_때_본인의_일기가_아니면_예외가_발생한다() throws Exception {
        Long diaryId = 1L;
        User user = UserFixture.createUser();
        UpdateDiaryRequest request = DiaryFixture.createUpdateDiaryRequest();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new AuthorityMismatchException()).given(updateDiaryUseCase).execute(any(User.class), anyLong(), any(UpdateDiaryRequest.class));

        mockMvc.perform(put("/diaries/{diary-id}", diaryId)
                .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
        )

                .andExpect(status().isForbidden())

                .andDo(restDocs.document());

        verify(updateDiaryUseCase, times(1)).execute(any(User.class), anyLong(), any(UpdateDiaryRequest.class));
    }

    @Test
    void 자신이_작성한_감정일기를_삭제한다() throws Exception {
        Long diaryId = 1L;
        User user = UserFixture.createUser();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(deleteDiaryUseCase).execute(user, diaryId);

        mockMvc.perform(delete("/diaries/{diary-id}", diaryId)
                .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )

                .andExpect(status().isNoContent())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer Token")
                        ),
                        pathParameters(
                                parameterWithName("diary-id")
                                        .description("조회할 감정일기의 id")
                        )
                ));

        verify(deleteDiaryUseCase, times(1)).execute(any(User.class), anyLong());
    }

    @Test
    void 감정일기를_삭제할_때_본인의_일기가_아니면_예외가_발생한다() throws Exception {
        Long diaryId = 1L;
        User user = UserFixture.createUser();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new AuthorityMismatchException()).given(deleteDiaryUseCase).execute(any(User.class), anyLong());

        mockMvc.perform(delete("/diaries/{diary-id}", diaryId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isForbidden())

                .andDo(restDocs.document());

        verify(deleteDiaryUseCase, times(1)).execute(any(User.class), anyLong());
    }
}