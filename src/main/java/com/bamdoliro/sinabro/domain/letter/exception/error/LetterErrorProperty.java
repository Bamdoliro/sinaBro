package com.bamdoliro.sinabro.domain.letter.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum LetterErrorProperty implements ErrorProperty {
    LETTER_NOT_FOUND(HttpStatus.NOT_FOUND, "편지를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String message;
}
