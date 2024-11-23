package com.bamdoliro.sinabro.application.auth;

import com.bamdoliro.sinabro.domain.auth.service.GoogleAuthService;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.GoogleInformationAppClient;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.response.GoogleInformation;
import com.bamdoliro.sinabro.presentation.auth.dto.request.GoogleTokenRequest;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GoogleAuthAppUseCase {

    private final GoogleInformationAppClient googleInformationAppClient;
    private final GoogleAuthService googleAuthService;

    @Transactional
    public TokenResponse execute(GoogleTokenRequest request) {
        GoogleInformation information = googleInformationAppClient.getUserInformation(request.getToken());

        return googleAuthService.execute(information);
    }
}
