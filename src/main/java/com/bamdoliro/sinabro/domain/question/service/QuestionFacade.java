package com.bamdoliro.sinabro.domain.question.service;

import com.bamdoliro.sinabro.domain.question.domain.Question;
import com.bamdoliro.sinabro.domain.question.exception.QuestionNotFoundException;
import com.bamdoliro.sinabro.infrastructure.persistence.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class QuestionFacade {

    private final QuestionRepository questionRepository;

    public Question getQuestion(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(QuestionNotFoundException::new);
    }
}
