package com.bamdoliro.sinabro.shared.auth;

import com.bamdoliro.sinabro.domain.auth.exception.EmptyTokenException;
import com.bamdoliro.sinabro.domain.auth.exception.InvalidTokenException;
import com.bamdoliro.sinabro.shared.config.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class AuthenticationExtractor {

    private final JwtProperties jwtProperties;

    public String extract(NativeWebRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new EmptyTokenException();
        }

        if (!Objects.requireNonNull(authorizationHeader).startsWith(jwtProperties.getPrefix())) {
            throw new InvalidTokenException();
        }

        return authorizationHeader.replace(jwtProperties.getPrefix(), "").trim();
    }
}
