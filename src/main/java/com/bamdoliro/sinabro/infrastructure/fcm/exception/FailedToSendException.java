package com.bamdoliro.sinabro.infrastructure.fcm.exception;

import com.bamdoliro.sinabro.infrastructure.fcm.exception.error.FCMErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class FailedToSendException extends SinabroException {
    public FailedToSendException() { super(FCMErrorProperty.FAILED_TO_SEND); }
}
