package com.bamdoliro.sinabro.domain.character.domain.type;

import com.bamdoliro.sinabro.shared.property.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CharacterType implements EnumProperty {
    HEON("헌"),
    SOL("솔");

    private final String description;
}
