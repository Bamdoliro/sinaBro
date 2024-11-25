package com.bamdoliro.sinabro.domain.inquiry.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InquiryErrorProperty implements ErrorProperty {
    INQUIRY_NOT_FOUND(HttpStatus.NOT_FOUND, "문의를 찾을 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
