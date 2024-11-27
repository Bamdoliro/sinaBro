package com.bamdoliro.sinabro.domain.inquiry.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum InquiryErrorProperty implements ErrorProperty {
    INQUIRY_NOT_FOUND(HttpStatus.NOT_FOUND, "문의를 찾을 수 없습니다."),
    INVALID_INQUIRY_STATE(HttpStatus.CONFLICT, "진행 중이거나 완료된 문의는 수정하거나 삭제할 수 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
