package com.bamdoliro.sinabro.domain.user.exception;

import com.bamdoliro.sinabro.domain.user.exception.error.UserErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class UserAlreadyExistsException extends SinabroException {
    public UserAlreadyExistsException() {
        super(UserErrorProperty.USER_ALREADY_EXISTS);
    }
}
