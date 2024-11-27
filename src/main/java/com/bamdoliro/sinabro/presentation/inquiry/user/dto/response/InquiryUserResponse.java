package com.bamdoliro.sinabro.presentation.inquiry.user.dto.response;

import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.presentation.answer.dto.response.AnswerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class InquiryUserResponse {

    private Long id;
    private String title;
    private String content;
    private InquiryStatus status;
    private List<AnswerResponse> answerResponseList;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public InquiryUserResponse(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.title = inquiry.getTitle();
        this.content = inquiry.getContent();
        this.status = inquiry.getStatus();
        this.answerResponseList = inquiry.getAnswerList().stream()
                .map(AnswerResponse::new)
                .toList();
        this.createdAt = inquiry.getCreatedAt();
        this.updatedAt = inquiry.getUpdatedAt();
    }
}
