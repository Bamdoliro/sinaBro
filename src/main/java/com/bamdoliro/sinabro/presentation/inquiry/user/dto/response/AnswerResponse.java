package com.bamdoliro.sinabro.presentation.inquiry.user.dto.response;

import com.bamdoliro.sinabro.domain.inquiry.domain.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerResponse {
    private String content;
    private String username;

    public AnswerResponse(Answer answer) {
        this.content = answer.getContent();
        this.username = answer.getUser().getName();
    }
}
