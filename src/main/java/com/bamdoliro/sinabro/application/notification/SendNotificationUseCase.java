package com.bamdoliro.sinabro.application.notification;

import com.bamdoliro.sinabro.domain.fcm.token.service.FCMTokenFacade;
import com.bamdoliro.sinabro.domain.notification.domain.Notification;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.fcm.FCMService;
import com.bamdoliro.sinabro.infrastructure.persistence.notification.NotificationRepository;
import com.bamdoliro.sinabro.presentation.notification.dto.request.SendNotificationRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class SendNotificationUseCase {

    private final FCMService fcmService;
    private final NotificationRepository notificationRepository;
    private final FCMTokenFacade fcmTokenFacade;

    @Transactional
    public void execute(User user, SendNotificationRequest request) {
        fcmTokenFacade.getAllToken(user)
                .forEach((fcmToken) -> fcmService.sendMessageTo(fcmToken.getToken(), request.getTitle(), request.getBody()));

        Notification notification = new Notification(request.getTitle(), request.getBody(), user);

        notificationRepository.save(notification);
    }
}
