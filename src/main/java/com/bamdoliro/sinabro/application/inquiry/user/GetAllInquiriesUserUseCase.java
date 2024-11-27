package com.bamdoliro.sinabro.application.inquiry.user;

import com.bamdoliro.sinabro.domain.inquiry.domain.type.InquiryStatus;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.inquiry.InquiryRepository;
import com.bamdoliro.sinabro.presentation.inquiry.user.dto.response.ListInquiryUserResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class GetAllInquiriesUserUseCase {

    private final InquiryRepository inquiryRepository;

    public List<ListInquiryUserResponse> execute(User user, InquiryStatus status) {
        return inquiryRepository.findAllByUserAndStatus(user, status).stream()
                .map(ListInquiryUserResponse::new)
                .toList();
    }
}
