package com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleInformation {

    private String email;
    private String name;
}
