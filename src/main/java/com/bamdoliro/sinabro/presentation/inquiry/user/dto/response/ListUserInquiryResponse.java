package com.bamdoliro.sinabro.presentation.inquiry.user.dto.response;

import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ListUserInquiryResponse {

    private Long id;
    private String title;
    private InquiryStatus status;
    private LocalDateTime updatedAt;

    public ListUserInquiryResponse(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.title = inquiry.getTitle();
        this.status = inquiry.getStatus();
        this.updatedAt = inquiry.getUpdatedAt();
    }
}
