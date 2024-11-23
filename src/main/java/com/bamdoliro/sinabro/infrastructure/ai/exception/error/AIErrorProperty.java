package com.bamdoliro.sinabro.infrastructure.ai.exception.error;

import com.bamdoliro.sinabro.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AIErrorProperty implements ErrorProperty {
    FAILED_TO_ANALYZE_DIARY(HttpStatus.INTERNAL_SERVER_ERROR, "일기 감정 분석에 실패했습니다."),
    FAILED_TO_GENERATE_LETTER(HttpStatus.INTERNAL_SERVER_ERROR, "편지 생성에 실패했습니다.");

    private final HttpStatus status;
    private final String message;
}
