package com.bamdoliro.sinabro.application.auth;

import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.config.properties.AuthProperties;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetGoogleAuthLinkUseCase {

    private final AuthProperties authProperties;

    private static final String QUERY_STRING = "?client_id=%s&redirect_uri=%s&response_type=code&" +
            "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";

    public String execute() {
        return authProperties.getGoogleBaseUrl()
                + String.format(QUERY_STRING, authProperties.getGoogleClientId(), authProperties.getGoogleRedirectUri());
    }
}
