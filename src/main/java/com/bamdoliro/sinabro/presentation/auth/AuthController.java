package com.bamdoliro.sinabro.presentation.auth;

import com.bamdoliro.sinabro.application.auth.GetGoogleAuthLinkUseCase;
import com.bamdoliro.sinabro.application.auth.GoogleAuthUseCase;
import com.bamdoliro.sinabro.presentation.auth.dto.response.TokenResponse;
import com.bamdoliro.sinabro.shared.response.CommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GetGoogleAuthLinkUseCase getGoogleAuthLinkUseCase;
    private final GoogleAuthUseCase googleAuthUseCase;

    @GetMapping("/google")
    public SingleCommonResponse<String> getGoogleAuthUrl() {
        return CommonResponse.success(
                getGoogleAuthLinkUseCase.execute()
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
}
