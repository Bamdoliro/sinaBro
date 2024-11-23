package com.bamdoliro.sinabro.infrastructure.ai.exception;

import com.bamdoliro.sinabro.infrastructure.ai.exception.error.AIErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class FailedToAnalyzeDiaryException extends SinabroException {
    public FailedToAnalyzeDiaryException() { super(AIErrorProperty.FAILED_TO_ANALYZE_DIARY); }
}
