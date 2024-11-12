package com.bamdoliro.sinabro.infrastructure.persistence.user;

import com.bamdoliro.sinabro.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
