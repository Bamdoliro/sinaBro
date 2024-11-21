package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.notification.domain.Notification;
import com.bamdoliro.sinabro.presentation.notification.dto.response.ListNotificationResponse;

public class NotificationFixture {

    public static Notification createNotification() {
        return new Notification("당신에게 편지가 왔어요.", "편지가 왔어요 어서 확인해보세요!", UserFixture.createUser());
    }

    public static ListNotificationResponse createListNotificationResponse() {
        return new ListNotificationResponse(createNotification());
    }
}
