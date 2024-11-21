package com.bamdoliro.sinabro.presentation.diary.dto.response;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DiaryResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DiaryResponse(Diary diary) {
        this.id = diary.getId();
        this.content = diary.getContent();
        this.createdAt = diary.getCreatedAt();
        this.updatedAt = diary.getUpdatedAt();
    }
}
