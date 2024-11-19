package com.bamdoliro.sinabro.domain.auth.domain.type;

import com.bamdoliro.sinabro.shared.enumeration.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenType implements EnumProperty {
    REFRESH_TOKEN("refresh token"),
    ACCESS_TOKEN("access token");

    private final String description;
}
