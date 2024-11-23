package com.bamdoliro.sinabro.presentation.question.dto.response;

import com.bamdoliro.sinabro.domain.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class QuestionResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public QuestionResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.createdAt = question.getCreatedAt();
        this.updatedAt = question.getUpdatedAt();
    }
}
