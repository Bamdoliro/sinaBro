package com.bamdoliro.sinabro.infrastructure.ai.feign.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnalyzeDiaryRequest {

    private String text;
}
