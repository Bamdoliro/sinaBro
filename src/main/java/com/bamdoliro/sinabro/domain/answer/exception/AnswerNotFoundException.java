package com.bamdoliro.sinabro.domain.answer.exception;

import com.bamdoliro.sinabro.domain.answer.exception.error.AnswerErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class AnswerNotFoundException extends SinabroException {
    public AnswerNotFoundException() {
        super(AnswerErrorProperty.ANSWER_NOT_FOUND);
    }
}
