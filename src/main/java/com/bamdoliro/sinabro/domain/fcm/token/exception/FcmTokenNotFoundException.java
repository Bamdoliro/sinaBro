package com.bamdoliro.sinabro.domain.fcm.token.exception;

import com.bamdoliro.sinabro.domain.fcm.token.exception.error.FcmTokenErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class FcmTokenNotFoundException extends SinabroException {
    public FcmTokenNotFoundException() { super(FcmTokenErrorProperty.FCM_TOKEN_NOTFOUND); }
}
