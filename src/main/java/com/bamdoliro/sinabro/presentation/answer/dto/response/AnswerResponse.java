package com.bamdoliro.sinabro.presentation.answer.dto.response;

import com.bamdoliro.sinabro.domain.answer.Answer;
import com.bamdoliro.sinabro.presentation.user.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AnswerResponse {
    private String content;
    private UserResponse user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AnswerResponse(Answer answer) {
        this.content = answer.getContent();
        this.user = new UserResponse(answer.getUser());
        this.createdAt = answer.getCreatedAt();
        this.updatedAt = answer.getUpdatedAt();
    }
}
