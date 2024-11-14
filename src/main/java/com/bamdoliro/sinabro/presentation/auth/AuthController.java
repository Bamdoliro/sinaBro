package com.bamdoliro.sinabro.presentation.auth;

import com.bamdoliro.sinabro.application.auth.GoogleAuthLinkUseCase;
import com.bamdoliro.sinabro.application.auth.GoogleAuthUseCase;
import com.bamdoliro.sinabro.application.auth.RefreshAccessTokenUseCase;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.response.CommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final GoogleAuthLinkUseCase googleAuthLinkUseCase;
    private final GoogleAuthUseCase googleAuthUseCase;
    private final RefreshAccessTokenUseCase refreshAccessTokenUseCase;

    @GetMapping("/google")
    public SingleCommonResponse<String> getGoogleAuthUrl() {
        return CommonResponse.success(
                googleAuthLinkUseCase.execute()
        );
    }

    // TODO 프론트엔드로 리다이렉트 후 백엔드로 Post 요청 전송
    // 백엔드에서 직접 GET으로 받으면 보안성이 저하될 수 있고, RESTful하지 않음
    // 프론트엔드로 리다이렉트 되게 한 후, code를 Request Body에 답아 Post로 전송하는 방식으로 수정해야함
    @GetMapping("/oauth2/code/google")
    public SingleCommonResponse<TokenResponse> authGoogle(@RequestParam String code) {
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
}
