package com.bamdoliro.sinabro.shared.response;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
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

    public IdResponse(Question question) {
        this.id = question.getId();
    }
}
