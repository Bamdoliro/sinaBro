package com.bamdoliro.sinabro.application.auth;

import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.auth.TokenRepository;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class LogOutUseCase {

    private final TokenRepository tokenRepository;

    public void execute(User user) {
        tokenRepository.deleteById(user.getEmail());
    }
}
