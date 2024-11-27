package com.bamdoliro.sinabro.domain.user.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorProperty implements ErrorProperty {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자가 없습니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 가입한 사용자입니다."),
    VERIFYING_HAS_FAILED(HttpStatus.UNAUTHORIZED, "인증에 실패했습니다."),
    VERIFICATION_CODE_MISMATCH(HttpStatus.UNAUTHORIZED, "인증코드가 일치하지 않습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
