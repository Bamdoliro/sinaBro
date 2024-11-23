package com.bamdoliro.sinabro.application.auth;

import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.GoogleAuthClient;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.request.GoogleAuthRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.config.properties.GoogleOAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GetGoogleAccessTokenUseCase {

    private final GoogleAuthClient googleAuthClient;
    private final GoogleOAuthProperties googleOAuthProperties;

    @Transactional
    public String execute(String code) {
        return googleAuthClient
                .getAccessToken(createGoogleAuthRequest(code))
                .getAccessToken();
    }

    private GoogleAuthRequest createGoogleAuthRequest(String code) {
        return GoogleAuthRequest.builder()
                .code(code)
                .clientId(googleOAuthProperties.getWeb().getClientId())
                .redirectUri(googleOAuthProperties.getWeb().getRedirectUri())
                .clientSecret(googleOAuthProperties.getWeb().getClientSecret())
                .build();
    }
}
