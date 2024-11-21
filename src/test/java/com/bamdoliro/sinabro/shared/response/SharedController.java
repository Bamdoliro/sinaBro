package com.bamdoliro.sinabro.shared.response;

import com.bamdoliro.sinabro.domain.diary.domain.type.Emotion;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.domain.user.domain.type.Authority;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.enumeration.EnumProperty;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/shared")
@RestController
public class SharedController {

    @PermitAll
    @GetMapping("/enum")
    public EnumDocs findEnums() {
        return EnumDocs.builder()
                .authority(getDocs(Authority.values()))
                .emotion(getDocs(Emotion.values()))
                .emotionCategory(getDocs(Emotion.Category.values()))
                .build();
    }

    private Map<String, String> getDocs(EnumProperty[] properties) {
        return Arrays.stream(properties)
                .collect(Collectors.toMap(EnumProperty::name, EnumProperty::getDescription));
    }

    @GetMapping("/jwt")
    public SingleCommonResponse<String> jwt(
            @AuthenticationPrincipal User user
    ) {
        return CommonResponse.ok("success");
    }
}
