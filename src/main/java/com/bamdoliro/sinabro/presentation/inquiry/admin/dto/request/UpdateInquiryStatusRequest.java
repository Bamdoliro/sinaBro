package com.bamdoliro.sinabro.presentation.inquiry.admin.dto.request;

import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInquiryStatusRequest {

    @NotNull(message = "필수값입니다.")
    private InquiryStatus status;
}
