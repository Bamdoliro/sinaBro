package com.bamdoliro.sinabro.presentation.auth;

import com.bamdoliro.sinabro.application.auth.*;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.auth.dto.request.GoogleTokenRequest;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.response.CommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final GoogleAuthLinkUseCase googleAuthLinkUseCase;
    private final GetGoogleAccessTokenUseCase getGoogleAccessTokenUseCase;
    private final RefreshAccessTokenUseCase refreshAccessTokenUseCase;
    private final LogOutUseCase logOutUseCase;
    private final GoogleAuthWebUseCase googleAuthWebUseCase;
    private final GoogleAuthAppUseCase googleAuthAppUseCase;

    @GetMapping("/google/web/link")
    public SingleCommonResponse<String> getGoogleAuthUrl() {
        return CommonResponse.ok(
                googleAuthLinkUseCase.execute()
        );
    }

    @GetMapping("/google")
    public SingleCommonResponse<String> getGoogleAccessToken(@RequestParam String code) {
        return CommonResponse.ok(
                getGoogleAccessTokenUseCase.execute(code)
        );
    }

    @PostMapping("/google/web")
    public SingleCommonResponse<TokenResponse> authWithGoogleWeb(@RequestBody @Valid GoogleTokenRequest request) {
        return CommonResponse.ok(
                googleAuthWebUseCase.execute(request)
        );
    }

    @PostMapping("/google/app")
    public SingleCommonResponse<TokenResponse> authWithGoogleApp(@RequestBody @Valid GoogleTokenRequest request) {
        return CommonResponse.ok(
                googleAuthAppUseCase.execute(request)
        );
    }

    @PostMapping("/refresh")
    public SingleCommonResponse<TokenResponse> refreshAccessToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        return CommonResponse.ok(
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
