package com.bamdoliro.sinabro.infrastructure.persistence.fcm.token;

import com.bamdoliro.sinabro.domain.fcm.token.domain.FCMToken;
import com.bamdoliro.sinabro.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FCMTokenRepository extends JpaRepository<FCMToken, Long> {
    List<FCMToken> findAllByUser(User user);
}
