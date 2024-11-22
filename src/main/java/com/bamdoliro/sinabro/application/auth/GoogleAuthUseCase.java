package com.bamdoliro.sinabro.application.auth;

import com.bamdoliro.sinabro.domain.auth.service.TokenService;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.domain.user.domain.type.Authority;
import com.bamdoliro.sinabro.infrastructure.feign.auth.google.GoogleAuthClient;
import com.bamdoliro.sinabro.infrastructure.feign.auth.google.GoogleInformationClient;
import com.bamdoliro.sinabro.infrastructure.feign.auth.google.dto.request.GoogleAuthRequest;
import com.bamdoliro.sinabro.infrastructure.feign.auth.google.dto.response.GoogleInformationResponse;
import com.bamdoliro.sinabro.infrastructure.persistence.user.UserRepository;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.config.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class GoogleAuthUseCase {

    private final GoogleAuthClient googleAuthClient;
    private final GoogleInformationClient googleInformationClient;
    private final AuthProperties authProperties;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Transactional
    public TokenResponse execute(String code) {
        String accessToken = googleAuthClient
                .getAccessToken(createGoogleAuthRequest(code))
                .getAccessToken();
        GoogleInformationResponse information = googleInformationClient.getUserInformation("Bearer " + accessToken);

        String email = information.getEmail();
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            userRepository.save(
                    new User(email, information.getName(), Authority.USER)
            );
        }

        return TokenResponse.builder()
                .accessToken(tokenService.generateAccessToken(email))
                .refreshToken(tokenService.generateRefreshToken(email))
                .build();
    }

    private GoogleAuthRequest createGoogleAuthRequest(String code) {
        return GoogleAuthRequest.builder()
                .code(code)
                .clientId(authProperties.getGoogleClientId())
                .redirectUri(authProperties.getGoogleRedirectUri())
                .clientSecret(authProperties.getGoogleClientSecret())
                .build();
    }
}
