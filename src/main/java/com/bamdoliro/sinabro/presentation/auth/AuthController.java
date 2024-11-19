package com.bamdoliro.sinabro.presentation.auth;

import com.bamdoliro.sinabro.application.auth.GoogleAuthLinkUseCase;
import com.bamdoliro.sinabro.application.auth.GoogleAuthUseCase;
import com.bamdoliro.sinabro.application.auth.LogOutUseCase;
import com.bamdoliro.sinabro.application.auth.RefreshAccessTokenUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.response.CommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final GoogleAuthLinkUseCase googleAuthLinkUseCase;
    private final GoogleAuthUseCase googleAuthUseCase;
    private final RefreshAccessTokenUseCase refreshAccessTokenUseCase;
    private final LogOutUseCase logOutUseCase;

    @GetMapping("/google/link")
    public SingleCommonResponse<String> getGoogleAuthUrl() {
        return CommonResponse.success(
                googleAuthLinkUseCase.execute()
        );
    }

    @PostMapping("/google")
    public SingleCommonResponse<TokenResponse> authWithGoogle(@RequestParam String code) {
        return CommonResponse.success(
                googleAuthUseCase.execute(code)
        );
    }

    @PostMapping("/refresh")
    public SingleCommonResponse<TokenResponse> refreshAccessToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        return CommonResponse.success(
                refreshAccessTokenUseCase.execute(refreshToken)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void logOut(
            @AuthenticationPrincipal User user
    ) {
        logOutUseCase.execute(user);
    }
}
