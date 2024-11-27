package com.bamdoliro.sinabro.domain.user.domain;

import com.bamdoliro.sinabro.shared.util.RandomCodeUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "signup-verification", timeToLive = 60 * 5)
public class Verification {

    @Id
    private String email;

    private String code;

    private Boolean isVerified;

    public Verification(String email) {
        this.email = email;
        this.code = RandomCodeUtil.generate(6);
        this.isVerified = false;
    }

    public void verify() {
        this.isVerified = true;
    }
}
