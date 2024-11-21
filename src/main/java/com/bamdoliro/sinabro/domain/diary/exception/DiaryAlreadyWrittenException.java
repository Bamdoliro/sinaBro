package com.bamdoliro.sinabro.domain.diary.exception;

import com.bamdoliro.sinabro.domain.diary.exception.error.DiaryErrorProperty;
import com.bamdoliro.sinabro.shared.error.SinabroException;

public class DiaryAlreadyWrittenException extends SinabroException {
    public DiaryAlreadyWrittenException() {
        super(DiaryErrorProperty.DIARY_ALREADY_WRITTEN);
    }
}
