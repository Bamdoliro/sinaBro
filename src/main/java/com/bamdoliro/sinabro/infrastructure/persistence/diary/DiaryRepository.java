package com.bamdoliro.sinabro.infrastructure.persistence.diary;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByAuthorAndWrittenAtBetween(User author, LocalDate writtenAtAfter, LocalDate writtenAtBefore);

    List<Diary> findAllByAuthorAndWrittenAtAfter(User author, LocalDate writtenAtAfter);

    List<Diary> findAllByAuthorAndWrittenAtBefore(User author, LocalDate writtenAtBefore);

    List<Diary> findAllByAuthor(User author);

    boolean existsByAuthorAndWrittenAt(User author, LocalDate writtenAt);
}
