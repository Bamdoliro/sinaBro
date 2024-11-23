package com.bamdoliro.sinabro.domain.letter.exception;

import com.bamdoliro.sinabro.domain.letter.exception.error.LetterErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class LetterNotFoundException extends SinabroException {
    public LetterNotFoundException() { super(LetterErrorProperty.LETTER_NOT_FOUND); }
}
