package com.bamdoliro.sinabro.application.auth;

import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.config.properties.GoogleOAuthProperties;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GoogleAuthLinkUseCase {

    private final GoogleOAuthProperties googleOAuthProperties;

    private static final String QUERY_STRING = "?client_id=%s&redirect_uri=%s&response_type=code&" +
            "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

    public String execute() {
        return googleOAuthProperties.getGoogle().getBaseUrl()
                + String.format(QUERY_STRING, googleOAuthProperties.getGoogle().getClientId(), googleOAuthProperties.getGoogle().getRedirectUri());
    }
}
