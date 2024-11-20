package com.bamdoliro.sinabro.domain.character.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CharacterErrorProperty implements ErrorProperty {
    CHARACTER_NOT_FOUND(HttpStatus.NOT_FOUND, "캐릭터를 찾을 수 없습니다."),
    CHARACTER_ALREADY_SELECTED(HttpStatus.CONFLICT, "캐릭터는 한번만 선택할 수 있습니다.");

    private final HttpStatus status;
    private final String message;
}
