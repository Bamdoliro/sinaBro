package com.bamdoliro.sinabro.presentation.character.dto.response;

import com.bamdoliro.sinabro.domain.character.domain.Character;
import com.bamdoliro.sinabro.domain.character.domain.type.CharacterType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CharacterResponse {

    private final CharacterType type;

    private final Integer friendShip;

    private final LocalDateTime createdAt;

    public CharacterResponse(Character character) {
        this.type = character.getType();
        this.friendShip = character.getFriendship();
        this.createdAt = character.getCreatedAt();
    }
}
