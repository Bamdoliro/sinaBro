package com.bamdoliro.sinabro.application.inquiry.admin;

import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.infrastructure.persistence.inquiry.InquiryRepository;
import com.bamdoliro.sinabro.presentation.inquiry.admin.dto.response.ListInquiryAdminResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class GetAllInquiriesAdminUseCase {

    private final InquiryRepository inquiryRepository;

    public List<ListInquiryAdminResponse> execute(InquiryStatus status) {
        return inquiryRepository.findAllByStatus(status).stream()
                .map(ListInquiryAdminResponse::new)
                .toList();
    }
}
