package com.bamdoliro.sinabro.shared.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class SinabroException extends RuntimeException {

    private final ErrorProperty errorProperty;
}
