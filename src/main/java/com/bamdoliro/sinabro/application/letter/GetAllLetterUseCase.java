package com.bamdoliro.sinabro.application.letter;

import com.bamdoliro.sinabro.domain.letter.service.LetterFacade;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.letter.LetterRepository;
import com.bamdoliro.sinabro.presentation.letter.dto.response.ListLetterResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class GetAllLetterUseCase {

    private final LetterRepository letterRepository;

    public List<ListLetterResponse> execute(User user) {
        return letterRepository.findAllByUser(user).stream()
                .map(ListLetterResponse::new)
                .toList();
    }
}
