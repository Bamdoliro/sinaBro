package com.bamdoliro.sinabro.domain.answer.service;

import com.bamdoliro.sinabro.domain.answer.domain.Answer;
import com.bamdoliro.sinabro.domain.answer.exception.AnswerNotFoundException;
import com.bamdoliro.sinabro.infrastructure.persistence.inquiry.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AnswerFacade {

    private final AnswerRepository answerRepository;

    public Answer getAnswer(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(AnswerNotFoundException::new);
    }
}
