package com.bamdoliro.sinabro.presentation.notification;

import com.bamdoliro.sinabro.application.notification.SendNotificationUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.notification.dto.request.SendNotificationRequest;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/notifications")
@RestController
public class NotificationController {

    private final SendNotificationUseCase sendNotificationUseCase;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void sendNotification(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid SendNotificationRequest request
    ) {
        sendNotificationUseCase.execute(user, request);
    }
}
