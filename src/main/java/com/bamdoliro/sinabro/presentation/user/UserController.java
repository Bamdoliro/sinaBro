package com.bamdoliro.sinabro.presentation.user;

import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.user.dto.response.UserResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.response.CommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    @GetMapping
    public SingleCommonResponse<UserResponse> getUserInfo(
            @AuthenticationPrincipal User user
    ) {
        return CommonResponse.ok(
                new UserResponse(user)
        );
    }
}
