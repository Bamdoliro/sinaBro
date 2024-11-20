package com.bamdoliro.sinabro.domain.auth.exception;

import com.bamdoliro.sinabro.domain.auth.exception.error.AuthErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class InvalidTokenException extends SinabroException {
    public InvalidTokenException() {
        super(AuthErrorProperty.INVALID_TOKEN);
    }
}
