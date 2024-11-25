package com.bamdoliro.sinabro.infrastructure.persistence.inquiry;

import com.bamdoliro.sinabro.domain.inquiry.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
