package com.bamdoliro.sinabro.presentation.notification.dto.response;

import com.bamdoliro.sinabro.domain.notification.domain.Notification;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ListNotificationResponse {

    private final Long id;
    private final String title;
    private final String body;
    private final LocalDateTime createdAt;

    public ListNotificationResponse(Notification notification) {
        this.id = notification.getId();
        this.title = notification.getTitle();
        this.body = notification.getBody();
        this.createdAt = notification.getCreatedAt();
    }
}
