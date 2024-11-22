package com.bamdoliro.sinabro.domain.diary.exception;

import com.bamdoliro.sinabro.domain.diary.exception.error.DiaryErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class DiaryNotFoundException extends SinabroException {
    public DiaryNotFoundException() {
        super(DiaryErrorProperty.DIARY_NOT_FOUND);
    }
}
