package com.bamdoliro.sinabro.application.answer;

import com.bamdoliro.sinabro.domain.answer.domain.Answer;
import com.bamdoliro.sinabro.domain.answer.service.AnswerFacade;
import com.bamdoliro.sinabro.domain.auth.exception.AuthorityMismatchException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.answer.dto.request.AnswerRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class UpdateAnswerUseCase {

    private final AnswerFacade answerFacade;

    public void execute(User user, Long id, AnswerRequest request) {
        Answer answer = answerFacade.getAnswer(id);
        validate(user, answer);

        answer.update(request.getContent());
    }

    private void validate(User user, Answer answer) {
        if (!answer.isOwner(user)) {
            throw new AuthorityMismatchException();
        }
    }
}
