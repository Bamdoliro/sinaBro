package com.bamdoliro.sinabro.infrastructure.persistence.diary;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByAuthorAndCreatedAtBetween(User author, LocalDateTime createdAtAfter, LocalDateTime createdAtBefore);

    List<Diary> findAllByAuthorAndCreatedAtAfter(User author, LocalDateTime createdAtAfter);

    List<Diary> findAllByAuthorAndCreatedAtBefore(User author, LocalDateTime createdAtBefore);

    List<Diary> findAllByAuthor(User author);
}
