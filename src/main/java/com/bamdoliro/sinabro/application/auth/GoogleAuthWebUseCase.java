package com.bamdoliro.sinabro.application.auth;

import com.bamdoliro.sinabro.domain.auth.service.GoogleAuthService;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.GoogleInformationWebClient;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.response.GoogleInformation;
import com.bamdoliro.sinabro.presentation.auth.dto.request.IdTokenRequest;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class GoogleAuthWebUseCase {

    private final GoogleInformationWebClient googleInformationWebClient;
    private final GoogleAuthService googleAuthService;

    @Transactional
    public TokenResponse execute(IdTokenRequest request) {
        GoogleInformation information = googleInformationWebClient.getUserInformation("Bearer " + request.getIdToken());

        return googleAuthService.execute(information);
    }
}
