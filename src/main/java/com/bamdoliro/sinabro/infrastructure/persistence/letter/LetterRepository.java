package com.bamdoliro.sinabro.infrastructure.persistence.letter;

import com.bamdoliro.sinabro.domain.letter.domain.Letter;
import com.bamdoliro.sinabro.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LetterRepository extends JpaRepository<Letter, Long> {
    List<Letter> findAllByUser(User user);
}
