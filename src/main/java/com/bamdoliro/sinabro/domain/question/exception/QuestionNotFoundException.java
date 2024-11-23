package com.bamdoliro.sinabro.domain.question.exception;

import com.bamdoliro.sinabro.domain.question.exception.error.QuestionErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class QuestionNotFoundException extends SinabroException {
    public QuestionNotFoundException() {
        super(QuestionErrorProperty.QUESTION_NOT_FOUND);
    }
}
