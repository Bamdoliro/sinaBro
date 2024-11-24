package com.bamdoliro.sinabro.presentation.character;

import com.bamdoliro.sinabro.application.character.GetCharacterUseCase;
import com.bamdoliro.sinabro.application.character.SelectCharacterUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.character.dto.request.SelectCharacterRequest;
import com.bamdoliro.sinabro.presentation.character.dto.response.CharacterResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/characters")
@RestController
public class CharacterController {

    private final SelectCharacterUseCase selectCharacterUseCase;
    private final GetCharacterUseCase getCharacterUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void selectCharacter(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid SelectCharacterRequest request
            ) {
        selectCharacterUseCase.execute(user, request);
    }

    @GetMapping
    public SingleCommonResponse<CharacterResponse> getCharacter(
            @AuthenticationPrincipal User user
    ) {
        return SingleCommonResponse.ok(
                getCharacterUseCase.execute(user)
        );
    }
}
