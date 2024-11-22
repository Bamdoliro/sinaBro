package com.bamdoliro.sinabro.application.notification;

import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.notification.NotificationRepository;
import com.bamdoliro.sinabro.presentation.notification.dto.response.ListNotificationResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class QueryNotificationListUseCase {

    private final NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    public List<ListNotificationResponse> execute(User user) {
        return notificationRepository.findAllByUser(user)
                .stream()
                .map(ListNotificationResponse::new)
                .toList();
    }
}
