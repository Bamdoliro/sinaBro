package com.bamdoliro.sinabro.presentation.inquiry.admin.dto.response;

import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.presentation.user.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ListInquiryAdminResponse {

    private Long id;
    private String title;
    private InquiryStatus status;
    private UserResponse user;
    private LocalDateTime updatedAt;

    public ListInquiryAdminResponse(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.title = inquiry.getTitle();
        this.status = inquiry.getStatus();
        this.user = new UserResponse(inquiry.getUser());
        this.updatedAt = inquiry.getUpdatedAt();
    }
}
