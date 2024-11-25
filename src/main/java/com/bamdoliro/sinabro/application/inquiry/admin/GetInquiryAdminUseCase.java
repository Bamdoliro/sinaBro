package com.bamdoliro.sinabro.application.inquiry.admin;

import com.bamdoliro.sinabro.domain.inquiry.domain.service.InquiryFacade;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.response.InquiryAdminResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class GetInquiryAdminUseCase {

    private final InquiryFacade inquiryFacade;

    public InquiryAdminResponse execute(Long id) {
        return new InquiryAdminResponse(inquiryFacade.getInquiry(id));
    }
}
