package com.bamdoliro.sinabro.presentation.fcm.token.dto;

import com.bamdoliro.sinabro.application.fcm.token.DeleteFCMTokenUseCase;
import com.bamdoliro.sinabro.application.fcm.token.SaveFCMTokenUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.fcm.token.dto.request.DeleteFCMTokenRequest;
import com.bamdoliro.sinabro.presentation.fcm.token.dto.request.SaveFCMTokenRequest;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.auth.Authority;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/fcm-token")
@RestController
public class FCMTokenController {

    private final SaveFCMTokenUseCase saveFCMTokenUseCase;
    private final DeleteFCMTokenUseCase deleteFCMTokenUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveFCMToken(
            @AuthenticationPrincipal(authority = Authority.USER) User user,
            @RequestBody @Valid SaveFCMTokenRequest request
    ) {
        saveFCMTokenUseCase.execute(user, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteFCMToken(
            @AuthenticationPrincipal(authority = Authority.USER) User user,
            @RequestBody @Valid DeleteFCMTokenRequest request
    ) {
        deleteFCMTokenUseCase.execute(user, request);
    }
}
