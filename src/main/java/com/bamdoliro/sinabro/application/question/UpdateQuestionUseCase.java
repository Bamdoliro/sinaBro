package com.bamdoliro.sinabro.application.question;

import com.bamdoliro.sinabro.domain.question.domain.Question;
import com.bamdoliro.sinabro.domain.question.service.QuestionFacade;
import com.bamdoliro.sinabro.presentation.question.dto.request.QuestionRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class UpdateQuestionUseCase {

    private final QuestionFacade questionFacade;

    @Transactional
    public void execute(Long id, QuestionRequest request) {
        Question question = questionFacade.getQuestion(id);
        question.update(request.getTitle(), request.getContent());
    }
}
