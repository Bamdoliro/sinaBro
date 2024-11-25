package com.bamdoliro.sinabro.shared.response;

import com.bamdoliro.sinabro.domain.answer.domain.Answer;
import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.letter.domain.Letter;
import com.bamdoliro.sinabro.domain.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IdResponse {

    private Long id;

    public IdResponse(Diary diary) {
        this.id = diary.getId();
    }

    public IdResponse(Letter letter) {
        this.id = letter.getId();
    }

    public IdResponse(Question question) {
        this.id = question.getId();
    }

    public IdResponse(Inquiry inquiry) {
        this.id = inquiry.getId();
    }

    public IdResponse(Answer answer) {
        this.id = answer.getId();
    }
}
