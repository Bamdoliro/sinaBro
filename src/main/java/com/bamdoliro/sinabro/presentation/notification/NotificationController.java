package com.bamdoliro.sinabro.presentation.notification;

import com.bamdoliro.sinabro.application.notification.QueryNotificationListUseCase;
import com.bamdoliro.sinabro.application.notification.SendNotificationToAllUserUseCase;
import com.bamdoliro.sinabro.application.notification.SendNotificationUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.notification.dto.request.SendNotificationRequest;
import com.bamdoliro.sinabro.presentation.notification.dto.response.ListNotificationResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.auth.Authority;
import com.bamdoliro.sinabro.shared.response.CommonResponse;
import com.bamdoliro.sinabro.shared.response.ListCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/notifications")
@RestController
public class NotificationController {

    private final SendNotificationUseCase sendNotificationUseCase;
    private final SendNotificationToAllUserUseCase sendNotificationToAllUserUseCase;
    private final QueryNotificationListUseCase queryNotificationListUseCase;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping
    public void sendNotification(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid SendNotificationRequest request
    ) {
        sendNotificationUseCase.execute(user, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/all")
    public void sendNotificationToAllUser(
            @AuthenticationPrincipal(authority = Authority.ADMIN) User user,
            @RequestBody @Valid SendNotificationRequest request
    ) {
        sendNotificationToAllUserUseCase.execute(request);
    }

    @GetMapping
    public ListCommonResponse<ListNotificationResponse> queryNotification(
            @AuthenticationPrincipal User user
    ) {
        return CommonResponse.ok(
            queryNotificationListUseCase.execute(user)
        );
    }
}
