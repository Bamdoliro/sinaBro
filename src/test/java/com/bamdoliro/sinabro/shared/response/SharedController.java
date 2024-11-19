package com.bamdoliro.sinabro.shared.response;

import com.bamdoliro.sinabro.domain.character.domain.type.CharacterType;
import com.bamdoliro.sinabro.domain.user.domain.type.Authority;
import com.bamdoliro.sinabro.shared.property.EnumProperty;
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
                .characterType(getDocs(CharacterType.values()))
                .build();
    }

    private Map<String, String> getDocs(EnumProperty[] properties) {
        return Arrays.stream(properties)
                .collect(Collectors.toMap(EnumProperty::name, EnumProperty::getDescription));
    }
}
