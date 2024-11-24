package com.bamdoliro.sinabro.infrastructure.persistence.question;

import com.bamdoliro.sinabro.domain.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
