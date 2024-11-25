package com.bamdoliro.sinabro.domain.inquiry.exception;

import com.bamdoliro.sinabro.domain.inquiry.exception.error.InquiryErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class InvalidInquiryStateException extends SinabroException {
    public InvalidInquiryStateException() {
        super(InquiryErrorProperty.INVALID_INQUIRY_STATE);
    }
}
