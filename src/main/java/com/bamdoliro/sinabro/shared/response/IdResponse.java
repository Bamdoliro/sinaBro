package com.bamdoliro.sinabro.shared.response;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IdResponse {

    private Long id;

    public IdResponse(Diary diary) {
        this.id = diary.getId();
    }
}
