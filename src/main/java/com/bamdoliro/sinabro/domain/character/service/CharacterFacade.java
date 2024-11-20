package com.bamdoliro.sinabro.domain.character.service;

import com.bamdoliro.sinabro.domain.character.domain.Character;
import com.bamdoliro.sinabro.domain.character.exception.CharacterNotFoundException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.character.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CharacterFacade {

    private final CharacterRepository characterRepository;

    @Transactional(readOnly = true)
    public Character getCharacter(User user) {
        return characterRepository.findByUser(user)
                .orElseThrow(CharacterNotFoundException::new);
    }
}
