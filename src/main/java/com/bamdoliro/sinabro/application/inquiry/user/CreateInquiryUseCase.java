package com.bamdoliro.sinabro.application.inquiry.user;

import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.inquiry.InquiryRepository;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.request.InquiryRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class CreateInquiryUseCase {

    private final InquiryRepository inquiryRepository;

    @Transactional
    public IdResponse execute(User user, InquiryRequest request) {
        Inquiry inquiry = inquiryRepository.save(
                new Inquiry(request.getTitle(), request.getContent(), user)
        );

        return new IdResponse(inquiry);
    }
}
