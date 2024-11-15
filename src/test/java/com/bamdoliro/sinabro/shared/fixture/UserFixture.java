package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.user.domain.User;

public class UserFixture {

    public static User createUser() {
        return User.builder()
                .email("bamdoliro@gmail.com")
                .name("김밤돌")
                .build();
    }
}
