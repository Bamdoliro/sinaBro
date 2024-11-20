package com.bamdoliro.sinabro.domain.auth.exception;

import com.bamdoliro.sinabro.domain.auth.exception.error.AuthErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class AuthorityMismatchException extends SinabroException {
    public AuthorityMismatchException() {
        super(AuthErrorProperty.AUTHORITY_MISMATCH);
    }
}
