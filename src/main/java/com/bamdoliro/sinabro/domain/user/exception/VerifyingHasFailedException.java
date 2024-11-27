package com.bamdoliro.sinabro.domain.user.exception;

import com.bamdoliro.sinabro.domain.user.exception.error.UserErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class VerifyingHasFailedException extends SinabroException {
    public VerifyingHasFailedException() {
        super(UserErrorProperty.VERIFYING_HAS_FAILED);
    }
}
