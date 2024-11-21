package com.bamdoliro.sinabro.domain.diary.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DiaryErrorProperty implements ErrorProperty {
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "감정일기를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
