package com.bamdoliro.sinabro.domain.answer.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AnswerErrorProperty implements ErrorProperty {
    ANSWER_NOT_FOUND(HttpStatus.NOT_FOUND, "답변을 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
