package com.bamdoliro.sinabro.application.inquiry.user;

import com.bamdoliro.sinabro.domain.auth.exception.AuthorityMismatchException;
import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.service.InquiryFacade;
import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.domain.inquiry.exception.InvalidInquiryStateException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.request.InquiryRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class UpdateInquiryUseCase {

    private final InquiryFacade inquiryFacade;

    @Transactional
    public void execute(User user, Long id, InquiryRequest request) {
        Inquiry inquiry = inquiryFacade.getInquiry(id);
        validate(user, inquiry);

        inquiry.update(request.getTitle(), request.getContent());
    }

    private void validate(User user, Inquiry inquiry) {
        if (!inquiry.isOwner(user)) {
            throw new AuthorityMismatchException();
        } else if (inquiry.getStatus() != InquiryStatus.WAITING) {
            throw new InvalidInquiryStateException();
        }
    }
}
