package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.character.domain.Character;
import com.bamdoliro.sinabro.domain.character.domain.type.CharacterType;
import com.bamdoliro.sinabro.presentation.character.dto.response.CharacterResponse;

public class CharacterFixture {

    public static Character createCharacter() {
        return new Character(
                CharacterType.HEON,
                UserFixture.createUser()
        );
    }

    public static CharacterResponse createCharacterResponse() { return new CharacterResponse(createCharacter()); }
}
