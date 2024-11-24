package com.bamdoliro.sinabro.application.question;

import com.bamdoliro.sinabro.domain.question.domain.Question;
import com.bamdoliro.sinabro.infrastructure.persistence.question.QuestionRepository;
import com.bamdoliro.sinabro.presentation.question.dto.request.QuestionRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class CreateQuestionUseCase {

    private final QuestionRepository questionRepository;

    public IdResponse execute(QuestionRequest request) {
        Question question = questionRepository.save(
                new Question(request.getTitle(), request.getContent())
        );

        return new IdResponse(question);
    }
}
