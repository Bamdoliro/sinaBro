package com.bamdoliro.sinabro.presentation.inquiry.user.dto.response;

import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.presentation.user.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class InquiryAdminResponse {

    private Long id;
    private String title;
    private String content;
    private InquiryStatus status;
    private List<AnswerResponse> answerResponseList;
    private UserResponse user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public InquiryAdminResponse(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.title = inquiry.getTitle();
        this.content = inquiry.getContent();
        this.status = inquiry.getStatus();
        this.answerResponseList = inquiry.getAnswerList().stream()
                .map(AnswerResponse::new)
                .toList();
        this.user = new UserResponse(inquiry.getUser());
        this.createdAt = inquiry.getCreatedAt();
        this.updatedAt = inquiry.getUpdatedAt();
    }
}
