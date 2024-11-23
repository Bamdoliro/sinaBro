package com.bamdoliro.sinabro.presentation.diary.dto.request;

import com.bamdoliro.sinabro.domain.diary.domain.type.Emotion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryRequest {

    @NotBlank(message = "필수값입니다.")
    private String content;

    @NotNull(message = "필수값입니다.")
    @Size(min = 1, max = 3, message = "감정은 1~3개까지 추가할 수 있습니다.")
    private List<Emotion> emotionList;

    @NotNull(message = "필수값입니다.")
    private LocalDate writtenAt;
}
