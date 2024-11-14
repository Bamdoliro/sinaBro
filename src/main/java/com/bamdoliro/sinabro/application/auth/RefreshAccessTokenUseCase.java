package com.bamdoliro.sinabro.application.auth;

import com.bamdoliro.sinabro.domain.auth.domain.Token;
import com.bamdoliro.sinabro.domain.auth.domain.type.TokenType;
import com.bamdoliro.sinabro.domain.auth.service.TokenService;
import com.bamdoliro.sinabro.infrastructure.persistence.auth.TokenRepository;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@UseCase
public class RefreshAccessTokenUseCase {

    private final TokenService tokenService;
    private final TokenRepository tokenRepository;

    @Transactional(readOnly = true)
    public TokenResponse execute(String refreshToken) {
        validate(refreshToken);
        Token token = getToken(refreshToken);

        return TokenResponse.builder()
                .accessToken(token.getToken())
                .build();
    }

    private void validate(String token) {
        if (!Objects.equals(tokenService.getType(token), TokenType.REFRESH_TOKEN.name())) {
            throw new IllegalArgumentException();
        }
    }

    private Token getToken(String refreshToken) {
        String id = tokenService.getId(refreshToken);
        Token token = tokenRepository.findById(id)
                .orElseThrow();
        validate(refreshToken, token.getToken());

        return token;
    }

    private void validate(String expectedToken, String actualToken) {
        if (!Objects.equals(expectedToken, actualToken)) {
            throw new IllegalArgumentException();
        }
    }
}
