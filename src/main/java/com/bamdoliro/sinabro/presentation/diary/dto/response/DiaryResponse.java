package com.bamdoliro.sinabro.presentation.diary.dto.response;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.diary.domain.type.Emotion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class DiaryResponse {

    private Long id;
    private String content;
    private List<Emotion> emotionList;
    private LocalDate writtenAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DiaryResponse(Diary diary) {
        this.id = diary.getId();
        this.content = diary.getContent();
        this.emotionList = diary.getEmotionList();
        this.writtenAt = diary.getWrittenAt();
        this.createdAt = diary.getCreatedAt();
        this.updatedAt = diary.getUpdatedAt();
    }
}
