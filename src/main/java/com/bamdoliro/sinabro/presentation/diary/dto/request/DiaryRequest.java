package com.bamdoliro.sinabro.presentation.diary.dto.request;

import com.bamdoliro.sinabro.domain.diary.domain.type.Emotion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRequest {

    @NotBlank
    private String content;

    @Size(min = 1, max = 3, message = "감정은 1~3개까지 추가할 수 있습니다.")
    private List<Emotion> emotionList;
}
