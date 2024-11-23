package com.bamdoliro.sinabro.infrastructure.ai.exception;

import com.bamdoliro.sinabro.infrastructure.ai.exception.error.AIErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class FailedToGenerateLetterException extends SinabroException {
    public FailedToGenerateLetterException() { super(AIErrorProperty.FAILED_TO_GENERATE_LETTER); }
}
