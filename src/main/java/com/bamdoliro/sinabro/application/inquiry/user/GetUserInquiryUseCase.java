package com.bamdoliro.sinabro.application.inquiry.user;

import com.bamdoliro.sinabro.domain.auth.exception.AuthorityMismatchException;
import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.service.InquiryFacade;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.response.UserInquiryResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class GetUserInquiryUseCase {

    private final InquiryFacade inquiryFacade;

    public UserInquiryResponse execute(User user, Long id) {
        Inquiry inquiry = inquiryFacade.getInquiry(id);
        validate(user, inquiry);

        return new UserInquiryResponse(inquiry);
    }

    private void validate(User user, Inquiry inquiry) {
        if (!inquiry.isOwner(user)) {
            throw new AuthorityMismatchException();
        }
    }
}
