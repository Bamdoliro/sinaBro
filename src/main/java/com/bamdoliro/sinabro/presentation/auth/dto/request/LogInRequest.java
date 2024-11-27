package com.bamdoliro.sinabro.presentation.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LogInRequest {

    @NotBlank(message = "필수값입니다.")
    private String email;

    @NotBlank(message = "필수값입니다.")
    private String password;
}
