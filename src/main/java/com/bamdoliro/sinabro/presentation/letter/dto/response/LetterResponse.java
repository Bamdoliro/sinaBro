package com.bamdoliro.sinabro.presentation.letter.dto.response;

import com.bamdoliro.sinabro.domain.letter.domain.Letter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LetterResponse {

    private Long id;

    private String content;

    private LocalDateTime createdAt;

    public LetterResponse(Letter letter) {
        this.id = letter.getId();
        this.content = letter.getContent();
        this.createdAt = letter.getCreatedAt();
    }
}
