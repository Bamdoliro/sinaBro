package com.bamdoliro.sinabro.infrastructure.persistence.user;

public interface VerificationRedisRepository {

    void updateSignUpVerification(String email, boolean verified);
}
