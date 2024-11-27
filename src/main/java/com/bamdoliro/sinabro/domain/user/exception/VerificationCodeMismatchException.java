package com.bamdoliro.sinabro.domain.user.exception;

import com.bamdoliro.sinabro.domain.user.exception.error.UserErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class VerificationCodeMismatchException extends SinabroException {
    public VerificationCodeMismatchException() {
        super(UserErrorProperty.VERIFICATION_CODE_MISMATCH);
    }
}
