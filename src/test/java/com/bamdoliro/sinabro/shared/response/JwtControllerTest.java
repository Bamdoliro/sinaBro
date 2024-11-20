package com.bamdoliro.sinabro.shared.response;

import com.bamdoliro.sinabro.domain.auth.exception.AuthorityMismatchException;
import com.bamdoliro.sinabro.domain.auth.exception.EmptyTokenException;
import com.bamdoliro.sinabro.domain.auth.exception.ExpiredTokenException;
import com.bamdoliro.sinabro.domain.auth.exception.InvalidTokenException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.domain.user.exception.UserNotFoundException;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.fixture.UserFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JwtControllerTest extends RestDocsTestSupport {

    @Test
    void 인증에_성공한다() throws Exception {
        User user = UserFixture.createUser();
        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);

        mockMvc.perform(get("/shared/jwt")
                .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
        )

                .andExpect(status().isOk())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer Token")
                        )
                ));
    }

    @Test
    void 토큰이_만료되어_인증에_실패한다() throws Exception {
        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willThrow(new ExpiredTokenException());

        mockMvc.perform(get("/shared/jwt")
                .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
        )

                .andExpect(status().isUnauthorized())

                .andDo(restDocs.document());
    }

    @Test
    void 유효하지_않은_토큰을_전달하면_인증에_실패한다() throws Exception {
        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willThrow(new InvalidTokenException());

        mockMvc.perform(get("/shared/jwt")
                .header(HttpHeaders.AUTHORIZATION, jwtProperties.getPrefix() + " " + AuthFixture.createWeirdTokenString())
                .contentType(MediaType.APPLICATION_JSON)
        )

                .andExpect(status().isUnauthorized())

                .andDo(restDocs.document());
    }

    @Test
    void 토큰을_전달하지_않으면_인증에_실패한다() throws Exception {
        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willThrow(new EmptyTokenException());

        mockMvc.perform(get("/shared/jwt")
                .header(HttpHeaders.AUTHORIZATION, jwtProperties.getPrefix() + " " + AuthFixture.createWeirdTokenString())
                .contentType(MediaType.APPLICATION_JSON)
        )

                .andExpect(status().isUnauthorized())

                .andDo(restDocs.document());
    }

    @Test
    void 권한이_없다면_에러가_발생한다() throws Exception {
        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willThrow(new AuthorityMismatchException());

        mockMvc.perform(get("/shared/jwt")
                .header(HttpHeaders.AUTHORIZATION, jwtProperties.getPrefix() + " " + AuthFixture.createWeirdTokenString())
                .contentType(MediaType.APPLICATION_JSON)
        )

                .andExpect(status().isUnauthorized())

                .andDo(restDocs.document());
    }

    @Test
    void 유저가_없다면_에러가_발생한다() throws Exception {
        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willThrow(new UserNotFoundException());

        mockMvc.perform(get("/shared/jwt")
                .header(HttpHeaders.AUTHORIZATION, jwtProperties.getPrefix() + " " + AuthFixture.createWeirdTokenString())
                .contentType(MediaType.APPLICATION_JSON)
        )

                .andExpect(status().isNotFound())

                .andDo(restDocs.document());
    }
}
