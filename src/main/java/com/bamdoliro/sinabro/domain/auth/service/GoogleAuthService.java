package com.bamdoliro.sinabro.domain.auth.service;

import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.domain.user.domain.type.Authority;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.response.GoogleInformation;
import com.bamdoliro.sinabro.infrastructure.persistence.user.UserRepository;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    public TokenResponse execute(GoogleInformation information) {
        String email = information.getEmail();
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            userRepository.save(
                    User.builder()
                            .email(email)
                            .name(information.getName())
                            .authority(Authority.USER)
                            .build()
            );
        }

        return TokenResponse.builder()
                .accessToken(tokenService.generateAccessToken(email))
                .refreshToken(tokenService.generateRefreshToken(email))
                .build();
    }
}
