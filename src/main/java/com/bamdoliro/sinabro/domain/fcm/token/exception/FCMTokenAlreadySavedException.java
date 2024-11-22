package com.bamdoliro.sinabro.domain.fcm.token.exception;

import com.bamdoliro.sinabro.domain.fcm.token.exception.error.FCMTokenErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class FCMTokenAlreadySavedException extends SinabroException {
    public FCMTokenAlreadySavedException() { super(FCMTokenErrorProperty.FCM_TOKEN_ALREADY_SAVED); }
}
