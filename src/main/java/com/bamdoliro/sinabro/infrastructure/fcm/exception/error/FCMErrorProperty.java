package com.bamdoliro.sinabro.infrastructure.fcm.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum FCMErrorProperty implements ErrorProperty {
    FAILED_TO_SEND(HttpStatus.INTERNAL_SERVER_ERROR, "알림 전송에 실패했습니다.");

    private final HttpStatus status;
    private final String message;
}
