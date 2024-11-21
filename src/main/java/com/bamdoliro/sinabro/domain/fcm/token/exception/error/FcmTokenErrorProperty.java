package com.bamdoliro.sinabro.domain.fcm.token.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FcmTokenErrorProperty implements ErrorProperty {
    FCM_TOKEN_NOTFOUND(HttpStatus.NOT_FOUND, "FCM 토큰을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
