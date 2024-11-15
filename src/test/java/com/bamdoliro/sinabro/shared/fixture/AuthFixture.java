package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.auth.domain.Token;

public class AuthFixture {

    public static String createGoogleOAuthLink() {
        return "https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount";
    }

    public static String createGoogleCode() {
        return "this_is_code";
    }

    public static String createAccessTokenString() {
        return "new.access.token";
    }

    public static String createRefreshTokenString() {
        return "new.refresh.token";
    }

    public static Token createAccessToken() {
        return Token.builder()
                .id("bamdoliro@gmail.com")
                .token("new.access.token")
                .build();
    }

    public static Token createRefreshToken() {
        return Token.builder()
                .id("bamdoliro@gmail.com")
                .token("new.access.token")
                .build();
    }

    public static String createAuthHeader() {
        return "Bearer it's.access.token";
    }
}
