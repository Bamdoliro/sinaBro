package com.bamdoliro.sinabro.domain.inquiry.domain.service;

import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import com.bamdoliro.sinabro.domain.inquiry.exception.InquiryNotFoundException;
import com.bamdoliro.sinabro.infrastructure.persistence.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InquiryFacade {

    private final InquiryRepository inquiryRepository;

    public Inquiry getInquiry(Long id) {
        return inquiryRepository.findById(id)
                .orElseThrow(InquiryNotFoundException::new);
    }
}
