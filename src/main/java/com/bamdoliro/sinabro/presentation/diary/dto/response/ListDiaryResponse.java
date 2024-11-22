package com.bamdoliro.sinabro.presentation.diary.dto.response;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ListDiaryResponse {

    private final Long id;
    private final LocalDate writtenAt;

    public ListDiaryResponse(Diary diary) {
        this.id = diary.getId();
        this.writtenAt = diary.getWrittenAt();
    }
}
