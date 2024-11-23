package com.bamdoliro.sinabro.application.letter;

import com.bamdoliro.sinabro.domain.letter.domain.Letter;
import com.bamdoliro.sinabro.domain.letter.service.LetterFacade;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.letter.dto.response.LetterResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class GetLetterUseCase {

    private final LetterFacade letterFacade;

    public LetterResponse execute(User user, Long id) {
        Letter letter = letterFacade.getLetter(user, id);

        return new LetterResponse(letter);
    }
}
