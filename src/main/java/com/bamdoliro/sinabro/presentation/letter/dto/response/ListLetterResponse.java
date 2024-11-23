package com.bamdoliro.sinabro.presentation.letter.dto.response;

import com.bamdoliro.sinabro.domain.letter.domain.Letter;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ListLetterResponse {

    private final Long id;

    private final LocalDateTime createdAt;

    public ListLetterResponse(Letter letter) {
        this.id = letter.getId();
        this.createdAt = letter.getCreatedAt();
    }
}
