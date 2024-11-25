package com.bamdoliro.sinabro.infrastructure.persistence.inquiry;

import com.bamdoliro.sinabro.domain.inquiry.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
