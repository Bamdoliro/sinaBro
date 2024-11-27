package com.bamdoliro.sinabro.infrastructure.persistence.user;

import com.bamdoliro.sinabro.domain.user.domain.SignUpVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.PartialUpdate;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class VerificationRedisRepositoryImpl implements VerificationRedisRepository {

    private final RedisKeyValueTemplate template;

    @Override
    public void updateSignUpVerification(String email, boolean verified) {
        PartialUpdate<SignUpVerification> update = new PartialUpdate<>(email, SignUpVerification.class)
                .set("isVerified", verified)
                .refreshTtl(true);

        template.update(update);
    }
}
