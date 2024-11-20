package com.bamdoliro.sinabro.domain.user.exception;

import com.bamdoliro.sinabro.domain.user.exception.error.UserErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class UserNotFoundException extends SinabroException {
    public UserNotFoundException() {
        super(UserErrorProperty.USER_NOT_FOUND);
    }
}
