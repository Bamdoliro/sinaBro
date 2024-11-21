package com.bamdoliro.sinabro.presentation.notification.dto.request;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
public class SendNotificationRequest {

    private String title;

    private String body;
}

