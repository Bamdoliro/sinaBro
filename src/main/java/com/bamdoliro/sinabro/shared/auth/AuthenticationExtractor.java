package com.bamdoliro.sinabro.shared.auth;

import com.bamdoliro.sinabro.shared.config.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@RequiredArgsConstructor
@Component
public class AuthenticationExtractor {

    private final JwtProperties jwtProperties;

    public String extract(NativeWebRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            throw new IllegalStateException("Empty Token");
        }

        if (!authorizationHeader.startsWith(jwtProperties.getPrefix())) {
            throw new IllegalStateException("Invalid Token");
        }

        return authorizationHeader.replace(jwtProperties.getPrefix(), "").trim();
    }
}
