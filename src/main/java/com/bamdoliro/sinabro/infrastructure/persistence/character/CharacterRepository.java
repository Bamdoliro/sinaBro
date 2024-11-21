package com.bamdoliro.sinabro.infrastructure.persistence.character;

import com.bamdoliro.sinabro.domain.character.domain.Character;
import com.bamdoliro.sinabro.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character, Long> {

    Optional<Character> findByUser(User user);
    boolean existsByUser(User user);
}
