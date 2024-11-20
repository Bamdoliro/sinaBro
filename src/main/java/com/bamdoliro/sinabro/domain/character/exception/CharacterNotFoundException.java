package com.bamdoliro.sinabro.domain.character.exception;


import com.bamdoliro.sinabro.domain.character.exception.error.CharacterErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class CharacterNotFoundException extends SinabroException {
    public CharacterNotFoundException() { super(CharacterErrorProperty.CHARACTER_NOT_FOUND); }
}
