package com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GoogleAuthRequest {

    private final String code;
    private final String clientId;
    private final String redirectUri;
    private final String clientSecret;
    private final String grantType = "authorization_code";
}
