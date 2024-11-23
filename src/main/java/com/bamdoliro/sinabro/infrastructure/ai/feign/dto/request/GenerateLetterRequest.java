package com.bamdoliro.sinabro.infrastructure.ai.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GenerateLetterRequest {

    private Integer id;

    private Integer day;

    private List<EmotionAndKeyword> text;
}
