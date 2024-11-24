package com.bamdoliro.sinabro.application.auth;

import com.bamdoliro.sinabro.domain.auth.service.GoogleAuthService;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.GoogleAuthClient;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.GoogleInformationWebClient;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.request.GoogleAuthRequest;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.response.GoogleInformation;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.config.properties.GoogleOAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GoogleAuthWebUseCase {

    private final GoogleInformationWebClient googleInformationWebClient;
    private final GoogleAuthService googleAuthService;
    private final GoogleAuthClient googleAuthClient;
    private final GoogleOAuthProperties googleOAuthProperties;

    @Transactional
    public TokenResponse execute(String code) {
        String accessToken = googleAuthClient
                .getAccessToken(createGoogleAuthRequest(code))
                .getAccessToken();
        GoogleInformation information = googleInformationWebClient.getUserInformation("Bearer " + accessToken);

        return googleAuthService.execute(information);
    }

    private GoogleAuthRequest createGoogleAuthRequest(String code) {
        return GoogleAuthRequest.builder()
                .code(code)
                .clientId(googleOAuthProperties.getGoogle().getClientId())
                .redirectUri(googleOAuthProperties.getGoogle().getRedirectUri())
                .clientSecret(googleOAuthProperties.getGoogle().getClientSecret())
                .build();
    }
}
