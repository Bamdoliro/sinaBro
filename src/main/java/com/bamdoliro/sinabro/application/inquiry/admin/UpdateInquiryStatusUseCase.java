package com.bamdoliro.sinabro.application.inquiry.admin;

import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.domain.service.InquiryFacade;
import com.bamdoliro.sinabro.presentation.inquiry.admin.dto.request.UpdateInquiryStatusRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class UpdateInquiryStatusUseCase {

    private final InquiryFacade inquiryFacade;

    @Transactional
    public void execute(Long id, UpdateInquiryStatusRequest request) {
        Inquiry inquiry = inquiryFacade.getInquiry(id);
        inquiry.setStatus(request.getStatus());
    }
}
