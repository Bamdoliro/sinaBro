package com.bamdoliro.sinabro.presentation.question.dto.response;

import com.bamdoliro.sinabro.domain.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ListQuestionResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime updatedAt;

    public ListQuestionResponse(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.updatedAt = question.getUpdatedAt();
    }
}
