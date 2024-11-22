package com.bamdoliro.sinabro.domain.fcm.token.service;

import com.bamdoliro.sinabro.domain.fcm.token.domain.FCMToken;
import com.bamdoliro.sinabro.domain.fcm.token.exception.FCMTokenNotFoundException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.fcm.token.FCMTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FCMTokenFacade {

    private final FCMTokenRepository fcmTokenRepository;

    @Transactional(readOnly = true)
    public List<FCMToken> getAllToken(User user) {
        List<FCMToken> tokens = fcmTokenRepository.findAllByUser(user);
        if(tokens.isEmpty()) {
            throw new FCMTokenNotFoundException();
        }

        return tokens;
    }
}
