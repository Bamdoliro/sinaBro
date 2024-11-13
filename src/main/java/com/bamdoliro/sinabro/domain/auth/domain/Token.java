package com.bamdoliro.sinabro.domain.auth.domain;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 15)
public class Token {

    @Id
    private String id;

    private String token;

    @Builder
    public Token(String id, String token) {
        this.id = id;
        this.token = token;
    }
}
