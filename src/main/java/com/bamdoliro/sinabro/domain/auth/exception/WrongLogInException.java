package com.bamdoliro.sinabro.domain.auth.exception;

import com.bamdoliro.sinabro.domain.auth.exception.error.AuthErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class WrongLogInException extends SinabroException {
    public WrongLogInException() {
        super(AuthErrorProperty.WRONG_LOGIN);
    }
}
