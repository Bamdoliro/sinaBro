package com.bamdoliro.sinabro.domain.inquiry.domain.type;

import com.bamdoliro.sinabro.shared.enumeration.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InquiryStatus implements EnumProperty {
    WAITING("대기"),
    IN_PROGRESS("진행"),
    COMPLETED("완료")
    ;

    private final String description;
}
