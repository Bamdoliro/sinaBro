package com.bamdoliro.sinabro.application.question;

import com.bamdoliro.sinabro.infrastructure.persistence.question.QuestionRepository;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class DeleteQuestionUseCase {

    private final QuestionRepository questionRepository;

    @Transactional
    public void execute(Long id) {
        questionRepository.deleteById(id);
    }
}
