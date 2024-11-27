package com.bamdoliro.sinabro.domain.inquiry.exception;

import com.bamdoliro.sinabro.domain.inquiry.exception.error.InquiryErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class InquiryNotFoundException extends SinabroException {
    public InquiryNotFoundException() {
        super(InquiryErrorProperty.INQUIRY_NOT_FOUND);
    }
}
