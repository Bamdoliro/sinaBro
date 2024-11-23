package com.bamdoliro.sinabro.infrastructure.ai.feign.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnalyzeDiaryResponse {

    @JsonProperty("emotion")
    private String emotion;

    @JsonProperty("keyword")
    private String keyword;
}
