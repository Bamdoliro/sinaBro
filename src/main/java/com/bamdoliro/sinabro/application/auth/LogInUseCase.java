package com.bamdoliro.sinabro.application.auth;

import com.bamdoliro.sinabro.domain.auth.exception.PasswordMismatchException;
import com.bamdoliro.sinabro.domain.auth.exception.WrongLogInException;
import com.bamdoliro.sinabro.domain.auth.service.TokenService;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.domain.user.domain.value.Password;
import com.bamdoliro.sinabro.domain.user.exception.UserNotFoundException;
import com.bamdoliro.sinabro.domain.user.service.UserFacade;
import com.bamdoliro.sinabro.presentation.auth.dto.request.LogInRequest;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class LogInUseCase {

    private final UserFacade userFacade;
    private final TokenService tokenService;

    public TokenResponse execute(LogInRequest request) {
        User user;
        try {
            user = userFacade.getUser(request.getEmail());
            validate(request.getPassword(), user.getPassword());
        } catch (UserNotFoundException | PasswordMismatchException e) {
            throw new WrongLogInException();
        }

        return TokenResponse.builder()
                .accessToken(tokenService.generateAccessToken(user.getEmail()))
                .refreshToken(tokenService.generateRefreshToken(user.getEmail()))
                .build();
    }

    private void validate(String actual, Password expected) {
        if (!expected.match(actual)) {
            throw new PasswordMismatchException();
        }
    }
}
