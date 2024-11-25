package com.bamdoliro.sinabro.application.answer;

import com.bamdoliro.sinabro.domain.answer.Answer;
import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.service.InquiryFacade;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.inquiry.AnswerRepository;
import com.bamdoliro.sinabro.presentation.answer.dto.request.AnswerRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class CreateAnswerUseCase {

    private final AnswerRepository answerRepository;
    private final InquiryFacade inquiryFacade;

    public IdResponse execute(User user, Long id, AnswerRequest request) {
        Inquiry inquiry = inquiryFacade.getInquiry(id);
        Answer answer = answerRepository.save(
                new Answer(request.getContent(), inquiry, user)
        );

        return new IdResponse(answer);
    }
}
