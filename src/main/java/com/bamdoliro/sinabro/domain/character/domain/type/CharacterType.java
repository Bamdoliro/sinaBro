package com.bamdoliro.sinabro.domain.character.domain.type;

import com.bamdoliro.sinabro.shared.enumeration.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CharacterType implements EnumProperty {
    SOL("솔", 1),
    HEON("헌", 2);

    private final String description;
    private final Integer id;
}
