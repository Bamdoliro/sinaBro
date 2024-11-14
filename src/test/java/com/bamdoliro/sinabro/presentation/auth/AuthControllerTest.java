package com.bamdoliro.sinabro.presentation.auth;

import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest extends RestDocsTestSupport {

    @Test
    void 구글_로그인_링크를_발급받는다() throws Exception {
        given(googleAuthLinkUseCase.execute()).willReturn(AuthFixture.createGoogleOAuthLink());

        mockMvc.perform(get("/auth/google"))
                .andExpect(status().isOk())

                .andDo(restDocs.document());
    }

    @Test
    void 유저가_구글로_로그인한다() throws Exception {
        TokenResponse response = new TokenResponse(AuthFixture.createAccessTokenString(), AuthFixture.createRefreshTokenString());

        given(googleAuthUseCase.execute(any(String.class))).willReturn(response);

        mockMvc.perform(get("/auth/oauth2/code/google")
                        .param("code", AuthFixture.createGoogleCode())
                        .accept(MediaType.APPLICATION_JSON)
                )

        .andExpect(status().isOk())

        .andDo(restDocs.document());
    }
}