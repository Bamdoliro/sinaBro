package com.bamdoliro.sinabro.presentation.letter;

import com.bamdoliro.sinabro.domain.letter.exception.LetterNotFoundException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.ai.exception.FailedToGenerateLetterException;
import com.bamdoliro.sinabro.presentation.letter.dto.response.LetterResponse;
import com.bamdoliro.sinabro.presentation.letter.dto.response.ListLetterResponse;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.fixture.LetterFixture;
import com.bamdoliro.sinabro.shared.fixture.UserFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LetterControllerTest extends RestDocsTestSupport {

    @Test
    void 편지를_생성한다() throws Exception {
        User user = UserFixture.createUser();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);

        mockMvc.perform(post("/letters")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isCreated())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer token")
                        )
                ));

        verify(generateLetterUseCase, times(1)).execute(user);
    }

    @Test
    void 편지생성에_실패하면_오류가_발생한다() throws Exception {
        User user = UserFixture.createUser();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new FailedToGenerateLetterException()).given(generateLetterUseCase).execute(any(User.class));

        mockMvc.perform(post("/letters")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isInternalServerError())

                .andDo(restDocs.document());

        verify(generateLetterUseCase, times(1)).execute(user);
    }

    @Test
    void 유저가_편지를_전체_조회한다() throws Exception {
        User user = UserFixture.createUser();
        List<ListLetterResponse> response = List.of(
                LetterFixture.createListDiaryResponse(),
                LetterFixture.createListDiaryResponse(),
                LetterFixture.createListDiaryResponse()
        );

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getAllLetterUseCase.execute(any(User.class))).willReturn(response);

        mockMvc.perform(get("/letters")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer token")
                        )
                ));

        verify(getAllLetterUseCase, times(1)).execute(any(User.class));
    }

    @Test
    void 유저가_편지를_단일_조회한다() throws Exception {
        Long letterId = 1L;
        User user = UserFixture.createUser();
        LetterResponse response = LetterFixture.createLetterResponse();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        given(getLetterUseCase.execute(user, letterId)).willReturn(response);

        mockMvc.perform(get("/letters/{letter-id}", letterId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer token")
                        ),
                        pathParameters(
                                parameterWithName("letter-id")
                                        .description("조회할 편지의 id")
                        )
                ));

        verify(getLetterUseCase, times(1)).execute(user, letterId);
    }

    @Test
    void 유저가_편지를_단일_조회할때_조회할_편지가_없으면_에러가_발생한다() throws Exception {
        Long letterId = 1L;
        User user = UserFixture.createUser();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willThrow(new LetterNotFoundException()).given(getLetterUseCase).execute(any(User.class), anyLong());

        mockMvc.perform(get("/letters/{letter-id}", letterId)
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());

        verify(getLetterUseCase, times(1)).execute(user, letterId);
    }
}
