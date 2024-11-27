package com.bamdoliro.sinabro.application.answer;

import com.bamdoliro.sinabro.domain.answer.domain.Answer;
import com.bamdoliro.sinabro.domain.answer.service.AnswerFacade;
import com.bamdoliro.sinabro.domain.auth.exception.AuthorityMismatchException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.inquiry.AnswerRepository;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class DeleteAnswerUseCase {

    private final AnswerFacade answerFacade;
    private final AnswerRepository answerRepository;

    @Transactional
    public void execute(User user, Long id) {
        Answer answer = answerFacade.getAnswer(id);
        validate(user, answer);

        answerRepository.deleteById(id);
    }

    private void validate(User user, Answer answer) {
        if (!answer.isOwner(user)) {
            throw new AuthorityMismatchException();
        }
    }
}
