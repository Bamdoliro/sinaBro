package com.bamdoliro.sinabro.application.character;

import com.bamdoliro.sinabro.domain.character.domain.Character;
import com.bamdoliro.sinabro.domain.character.exception.CharacterAlreadySelectedException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.character.CharacterRepository;
import com.bamdoliro.sinabro.presentation.character.dto.request.SelectCharacterRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class SelectCharacterUseCase {

    private final CharacterRepository characterRepository;

    @Transactional
    public void execute(User user, SelectCharacterRequest request) {
        validate(user);

        Character character = Character.builder()
                .type(request.getType())
                .user(user)
                .build();

        characterRepository.save(character);
    }

    private void validate(User user) {
        Optional<Character> character = characterRepository.findByUser(user);
        if (character.isPresent()) {
            throw new CharacterAlreadySelectedException();
        }
    }
}
