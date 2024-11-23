package com.bamdoliro.sinabro.application.character;

import com.bamdoliro.sinabro.domain.character.service.CharacterFacade;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.character.dto.response.CharacterResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class GetCharacterUseCase {

    private final CharacterFacade characterFacade;

    public CharacterResponse execute(User user) {
        return new CharacterResponse(characterFacade.getCharacter(user));
    }
}
