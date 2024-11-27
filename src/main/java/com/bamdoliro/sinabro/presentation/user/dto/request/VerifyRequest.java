package com.bamdoliro.sinabro.presentation.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyRequest {

    @NotBlank(message = "필수값입니다.")
    @Email(message = "이메일 형식이어야 합니다")
    private String email;

    @NotBlank(message = "필수값입니다.")
    @Size(min = 6, max = 6, message = "6자여야 합니다.")
    private String code;
}
