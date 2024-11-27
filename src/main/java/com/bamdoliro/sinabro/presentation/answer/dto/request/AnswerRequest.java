package com.bamdoliro.sinabro.presentation.answer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest {

    @NotBlank(message = "필수값입니다.")
    @Size(max = 3000, message = "3000글자 이하여야 합니다.")
    private String content;
}
