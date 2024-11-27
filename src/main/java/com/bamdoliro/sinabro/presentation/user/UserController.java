package com.bamdoliro.sinabro.presentation.user;

import com.bamdoliro.sinabro.application.user.SendVerificationUseCase;
import com.bamdoliro.sinabro.application.user.SignUpUseCase;
import com.bamdoliro.sinabro.application.user.VerifyUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.user.dto.request.SendVerificationRequest;
import com.bamdoliro.sinabro.presentation.user.dto.request.SignUpRequest;
import com.bamdoliro.sinabro.presentation.user.dto.request.VerifyRequest;
import com.bamdoliro.sinabro.presentation.user.dto.response.UserResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.response.CommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final SignUpUseCase signUpUseCase;
    private final SendVerificationUseCase sendVerificationUseCase;
    private final VerifyUseCase verifyUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void signUp(
            @RequestBody @Valid SignUpRequest request
    ) {
        signUpUseCase.execute(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/verify")
    public void sendVerification(
            @RequestBody @Valid SendVerificationRequest request
    ) {
        sendVerificationUseCase.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/verify")
    public void verify(
            @RequestBody @Valid VerifyRequest request
    ) {
        verifyUseCase.execute(request);
    }

    @GetMapping
    public SingleCommonResponse<UserResponse> getUserInfo(
            @AuthenticationPrincipal User user
    ) {
        return CommonResponse.ok(
                new UserResponse(user)
        );
    }
}
