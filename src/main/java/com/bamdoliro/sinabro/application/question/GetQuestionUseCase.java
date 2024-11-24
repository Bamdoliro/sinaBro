package com.bamdoliro.sinabro.application.question;

import com.bamdoliro.sinabro.domain.question.service.QuestionFacade;
import com.bamdoliro.sinabro.presentation.question.dto.response.QuestionResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class GetQuestionUseCase {

    private final QuestionFacade questionFacade;

    public QuestionResponse execute(Long id) {
        return new QuestionResponse(
                questionFacade.getQuestion(id)
        );
    }
}
