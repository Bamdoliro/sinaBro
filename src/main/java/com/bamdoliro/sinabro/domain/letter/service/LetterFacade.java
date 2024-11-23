package com.bamdoliro.sinabro.domain.letter.service;

import com.bamdoliro.sinabro.domain.letter.domain.Letter;
import com.bamdoliro.sinabro.domain.letter.exception.LetterNotFoundException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.letter.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LetterFacade {

    private final LetterRepository letterRepository;

    @Transactional(readOnly = true)
    public Letter getLetter(User user, Long id) {
        return letterRepository.findByUserAndId(user, id)
                .orElseThrow(LetterNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Letter> getAllLetter(User user) {
        List<Letter> letters = letterRepository.findAllByUser(user);
        if(letters.isEmpty()) {
            throw new LetterNotFoundException();
        }

        return letters;
    }
}
