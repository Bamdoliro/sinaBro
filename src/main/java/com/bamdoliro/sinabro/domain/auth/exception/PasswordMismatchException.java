package com.bamdoliro.sinabro.domain.auth.exception;

import com.bamdoliro.sinabro.domain.auth.exception.error.AuthErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class PasswordMismatchException extends SinabroException {
    public PasswordMismatchException() {
        super(AuthErrorProperty.PASSWORD_MISMATCH);
    }
}
