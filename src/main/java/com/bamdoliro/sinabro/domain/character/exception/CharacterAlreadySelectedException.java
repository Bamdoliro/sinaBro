package com.bamdoliro.sinabro.domain.character.exception;

import com.bamdoliro.sinabro.domain.character.exception.error.CharacterErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class CharacterAlreadySelectedException extends SinabroException {
    public CharacterAlreadySelectedException() { super(CharacterErrorProperty.CHARACTER_ALREADY_SELECTED); }
}
