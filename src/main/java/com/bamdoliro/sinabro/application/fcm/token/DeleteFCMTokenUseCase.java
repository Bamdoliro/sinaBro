package com.bamdoliro.sinabro.application.fcm.token;

import com.bamdoliro.sinabro.infrastructure.persistence.fcm.token.FCMTokenRepository;
import com.bamdoliro.sinabro.presentation.fcm.token.dto.request.DeleteFCMTokenRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class DeleteFCMTokenUseCase {

    private final FCMTokenRepository fcmTokenRepository;

    @Transactional
    public void execute(DeleteFCMTokenRequest request) {
        fcmTokenRepository.deleteByToken(request.getToken());
    }
}
