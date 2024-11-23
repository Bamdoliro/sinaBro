package com.bamdoliro.sinabro.infrastructure.ai.feign.dto.request;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmotionAndKeyword {

    private String emotion;

    private String keyword;

    public EmotionAndKeyword(Diary diary) {
        this.emotion = diary.getAnalyzedEmotion();
        this.keyword = diary.getKeyword();
    }
}
