package com.bamdoliro.sinabro.infrastructure.persistence.user;

import com.bamdoliro.sinabro.domain.user.domain.SignUpVerification;
import org.springframework.data.repository.CrudRepository;

public interface SignUpVerificationRepository extends CrudRepository<SignUpVerification, String>, VerificationRedisRepository {
}
