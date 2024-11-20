package com.bamdoliro.sinabro.domain.auth.exception;

import com.bamdoliro.sinabro.domain.auth.exception.error.AuthErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class ExpiredTokenException extends SinabroException {
    public ExpiredTokenException() {
        super(AuthErrorProperty.EXPIRED_TOKEN);
    }
}
