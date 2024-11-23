package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.domain.user.domain.type.Authority;

public class UserFixture {

    public static User createUser() {
        return new User("bamdoliro@gmail.com", "김밤돌", Authority.USER);
    }

    public static User createAdmin() {
        return new User("bamdoliro@gmail.com", "어다민", Authority.ADMIN);
    }
}
