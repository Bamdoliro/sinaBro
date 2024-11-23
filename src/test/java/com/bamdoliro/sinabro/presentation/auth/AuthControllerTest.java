package com.bamdoliro.sinabro.presentation.auth;

import com.bamdoliro.sinabro.domain.auth.exception.InvalidTokenException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.auth.dto.request.GoogleTokenRequest;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.fixture.UserFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends RestDocsTestSupport {

    @Test
    void 구글_로그인_링크를_발급받는다() throws Exception {
        given(googleAuthLinkUseCase.execute()).willReturn(AuthFixture.createGoogleOAuthLink());

        mockMvc.perform(get("/auth/google/web/link"))
                .andExpect(status().isOk())

                .andDo(restDocs.document());

        verify(googleAuthLinkUseCase, times(1)).execute();
    }

    @Test
    void 유저가_웹에서_구글_액세스_토큰을_발급받는다() throws Exception {
        given(getGoogleAccessTokenUseCase.execute(any(String.class))).willReturn(AuthFixture.createGoogleToken());

        mockMvc.perform(get("/auth/google")
                .queryParam("code", AuthFixture.createGoogleOAuthCode())
                .accept(MediaType.APPLICATION_JSON)
        )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        queryParameters(
                                parameterWithName("code")
                                        .description("Google OAuth 인증 코드. 리다이렉트시 url에 포함됨")
                        )
                ));
        verify(getGoogleAccessTokenUseCase, times(1)).execute(any(String.class));
    }

    @Test
    void 유저가_웹에서_구글로_로그인한다() throws Exception {
        GoogleTokenRequest request = new GoogleTokenRequest(AuthFixture.createGoogleToken());
        TokenResponse response = new TokenResponse(AuthFixture.createAccessTokenString(), AuthFixture.createRefreshTokenString());

        given(googleAuthWebUseCase.execute(any(GoogleTokenRequest.class))).willReturn(response);

        mockMvc.perform(post("/auth/google/web")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("token")
                                        .type(JsonFieldType.STRING)
                                        .description("구글에서 발급받은 액세스 토큰 혹은 아이디 토큰")
                        )
                ));

        verify(googleAuthWebUseCase, times(1)).execute(any(GoogleTokenRequest.class));
    }

    @Test
    void 유저가_앱에서_구글로_로그인한다() throws Exception {
        GoogleTokenRequest request = new GoogleTokenRequest(AuthFixture.createGoogleToken());
        TokenResponse response = new TokenResponse(AuthFixture.createAccessTokenString(), AuthFixture.createRefreshTokenString());

        given(googleAuthAppUseCase.execute(any(GoogleTokenRequest.class))).willReturn(response);

        mockMvc.perform(post("/auth/google/app")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("token")
                                        .type(JsonFieldType.STRING)
                                        .description("구글에서 발급받은 액세스 토큰 혹은 아이디 토큰")
                        )
                ));

        verify(googleAuthAppUseCase, times(1)).execute(any(GoogleTokenRequest.class));
    }

    @Test
    void 리프레시_토큰으로_액세스_토큰을_재발급한다() throws Exception {
        String refreshToken = "Bearer " + AuthFixture.createRefreshTokenString();
        TokenResponse response = TokenResponse.builder()
                .accessToken(AuthFixture.createAccessTokenString())
                .build();

        given(refreshAccessTokenUseCase.execute(refreshToken)).willReturn(response);

        mockMvc.perform(post("/auth/refresh")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, refreshToken)
        )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Refresh Token")
                        )
                ));

        verify(refreshAccessTokenUseCase, times(1)).execute(refreshToken);
    }

    @Test
    void 액세스_토큰으로_액세스_토큰을_재발급하면_예외가_발생한다() throws Exception {
        String accessToken = AuthFixture.createAccessTokenString();

        given(refreshAccessTokenUseCase.execute(accessToken)).willThrow(new InvalidTokenException());

        mockMvc.perform(post("/auth/refresh")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, accessToken)
                )

                .andExpect(status().isUnauthorized())

                .andDo(restDocs.document());

        verify(refreshAccessTokenUseCase, times(1)).execute(accessToken);
    }

    @Test
    void 유저가_로그아웃한다() throws Exception {
        User user = UserFixture.createUser();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(logOutUseCase).execute(user);

        mockMvc.perform(delete("/auth")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                )

                .andExpect(status().isNoContent())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer token")
                        )
                ));

        verify(logOutUseCase, times(1)).execute(user);
    }
}