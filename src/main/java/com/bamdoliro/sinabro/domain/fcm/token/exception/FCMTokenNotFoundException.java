package com.bamdoliro.sinabro.domain.fcm.token.exception;

import com.bamdoliro.sinabro.domain.fcm.token.exception.error.FCMTokenErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class FCMTokenNotFoundException extends SinabroException {
    public FCMTokenNotFoundException() { super(FCMTokenErrorProperty.FCM_TOKEN_NOT_FOUND); }
}
