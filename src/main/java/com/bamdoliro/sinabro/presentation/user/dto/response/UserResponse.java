package com.bamdoliro.sinabro.presentation.user.dto.response;

import com.bamdoliro.sinabro.domain.user.domain.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private final String email;
    private final String name;

    public UserResponse(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
