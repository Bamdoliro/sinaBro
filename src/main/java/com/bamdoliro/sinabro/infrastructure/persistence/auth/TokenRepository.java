package com.bamdoliro.sinabro.infrastructure.persistence.auth;

import com.bamdoliro.sinabro.domain.auth.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
}
