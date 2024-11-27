package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.domain.user.domain.type.Authority;

public class UserFixture {

    public static User createUser() {
        return User.builder()
                .email("bamdoliro@gmail.com")
                .name("김밤돌")
                .authority(Authority.USER)
                .build();
    }

    public static User createAdmin() {
        return User.builder()
                .email("bamdoliro@gmail.com")
                .name("어드민")
                .authority(Authority.ADMIN)
                .build();
    }
}
