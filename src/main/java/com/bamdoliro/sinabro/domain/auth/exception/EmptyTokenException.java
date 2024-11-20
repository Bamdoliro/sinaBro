package com.bamdoliro.sinabro.domain.auth.exception;

import com.bamdoliro.sinabro.domain.auth.exception.error.AuthErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class EmptyTokenException extends SinabroException {
    public EmptyTokenException() {
        super(AuthErrorProperty.EMPTY_TOKEN);
    }
}
