package com.bamdoliro.sinabro.application.inquiry.user;

import com.bamdoliro.sinabro.domain.auth.exception.AuthorityMismatchException;
import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.service.InquiryFacade;
import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.domain.inquiry.exception.InvalidInquiryStateException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.inquiry.InquiryRepository;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class DeleteInquiryUseCase {

    private final InquiryFacade inquiryFacade;
    private final InquiryRepository inquiryRepository;

    public void execute(User user, Long id) {
        Inquiry inquiry = inquiryFacade.getInquiry(id);
        validate(user, inquiry);

        inquiryRepository.deleteById(inquiry.getId());
    }

    private void validate(User user, Inquiry inquiry) {
        if (!inquiry.isOwner(user)) {
            throw new AuthorityMismatchException();
        } else if (inquiry.getStatus() != InquiryStatus.WAITING) {
            throw new InvalidInquiryStateException();
        }
    }
}
