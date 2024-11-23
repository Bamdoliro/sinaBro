package com.bamdoliro.sinabro.application.question;

import com.bamdoliro.sinabro.infrastructure.persistence.question.QuestionRepository;
import com.bamdoliro.sinabro.presentation.question.dto.response.ListQuestionResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class GetAllQuestionUseCase {

    private final QuestionRepository questionRepository;

    public List<ListQuestionResponse> execute() {
        return questionRepository.findAll()
                .stream()
                .map(ListQuestionResponse::new)
                .toList();
    }
}
