package com.bamdoliro.sinabro.presentation.diary.dto.response;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ListDiaryResponse {

    private final Long id;
    private final LocalDateTime createdAt;

    public ListDiaryResponse(Diary diary) {
        this.id = diary.getId();
        this.createdAt = diary.getCreatedAt();
    }
}
