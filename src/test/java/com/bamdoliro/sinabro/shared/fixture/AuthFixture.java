package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.auth.domain.Token;

public class AuthFixture {

    public static String createGoogleOAuthLink() {
        return "https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount";
    }

    public static String createGoogleOAuthCode() {
        return "this_is_code";
    }

    public static String createAccessTokenString() {
        return "sinabro.access.token";
    }

    public static String createRefreshTokenString() {
        return "sinabro.refresh.token";
    }

    public static String createWeirdTokenString() {
        return "sinabro.weird.token";
    }

    public static Token createAccessToken() {
        return Token.builder()
                .id("bamdoliro@gmail.com")
                .token("sinabro.access.token")
                .build();
    }

    public static Token createRefreshToken() {
        return Token.builder()
                .id("bamdoliro@gmail.com")
                .token("sinabro.access.token")
                .build();
    }

    public static String createAuthHeader() {
        return "Bearer sinabro.access.token";
    }
}
