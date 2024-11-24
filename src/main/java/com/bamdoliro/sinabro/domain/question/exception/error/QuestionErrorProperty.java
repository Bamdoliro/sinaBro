package com.bamdoliro.sinabro.domain.question.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum QuestionErrorProperty implements ErrorProperty {
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 질문을 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
