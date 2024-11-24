package com.bamdoliro.sinabro.application.notification;

import com.bamdoliro.sinabro.domain.notification.domain.Notification;
import com.bamdoliro.sinabro.infrastructure.fcm.FCMService;
import com.bamdoliro.sinabro.infrastructure.persistence.fcm.token.FCMTokenRepository;
import com.bamdoliro.sinabro.infrastructure.persistence.notification.NotificationRepository;
import com.bamdoliro.sinabro.presentation.notification.dto.request.SendNotificationRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class SendNotificationToAllUserUseCase {

    private final FCMService fcmService;
    private final NotificationRepository notificationRepository;
    private final FCMTokenRepository fcmTokenRepository;

    @Transactional
    public void execute(SendNotificationRequest request) {
        fcmTokenRepository.findAll()
                .forEach(fcmToken -> {
                    fcmService.sendMessageTo(fcmToken.getToken(), request.getTitle(), request.getBody());
                    notificationRepository.save(new Notification(request.getTitle(), request.getBody(), fcmToken.getUser()));
                });
    }
}
