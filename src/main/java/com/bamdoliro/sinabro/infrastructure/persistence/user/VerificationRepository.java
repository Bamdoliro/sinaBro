package com.bamdoliro.sinabro.infrastructure.persistence.user;

import com.bamdoliro.sinabro.domain.user.domain.Verification;
import org.springframework.data.repository.CrudRepository;

public interface VerificationRepository extends CrudRepository<Verification, String>, VerificationRedisRepository {
}
