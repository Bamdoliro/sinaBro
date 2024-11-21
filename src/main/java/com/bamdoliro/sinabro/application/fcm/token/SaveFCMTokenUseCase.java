package com.bamdoliro.sinabro.application.fcm.token;

import com.bamdoliro.sinabro.domain.fcm.token.domain.FCMToken;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.fcm.token.FCMTokenRepository;
import com.bamdoliro.sinabro.presentation.fcm.token.dto.request.SaveFCMTokenRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class SaveFCMTokenUseCase {

    private final FCMTokenRepository fcmTokenRepository;

    @Transactional
    public void execute(User user, SaveFCMTokenRequest request) {
        FCMToken fcmToken = new FCMToken(request.getToken(), user);

        fcmTokenRepository.save(fcmToken);
    }
}
