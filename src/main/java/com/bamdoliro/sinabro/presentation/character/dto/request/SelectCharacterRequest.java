package com.bamdoliro.sinabro.presentation.character.dto.request;

import com.bamdoliro.sinabro.domain.character.domain.type.CharacterType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SelectCharacterRequest {

    @NotNull(message = "필수값입니다.")
    private CharacterType type;
}
