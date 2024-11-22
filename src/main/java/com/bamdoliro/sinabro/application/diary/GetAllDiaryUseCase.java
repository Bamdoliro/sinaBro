package com.bamdoliro.sinabro.application.diary;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.diary.DiaryRepository;
import com.bamdoliro.sinabro.presentation.diary.dto.response.ListDiaryResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class GetAllDiaryUseCase {

    private final DiaryRepository diaryRepository;

    @Transactional(readOnly = true)
    public List<ListDiaryResponse> execute(User user, LocalDate startDate, LocalDate endDate) {
        List<Diary> diaryList;
        if (startDate != null && endDate != null) {
            diaryList = diaryRepository.findAllByAuthorAndWrittenAtBetween(user, startDate, endDate);
        } else if (startDate != null) {
            diaryList = diaryRepository.findAllByAuthorAndWrittenAtAfter(user, startDate);
        } else if (endDate != null) {
            diaryList = diaryRepository.findAllByAuthorAndWrittenAtBefore(user, endDate);
        } else {
            diaryList = diaryRepository.findAllByAuthor(user);
        }

         return diaryList.stream()
                .map(ListDiaryResponse::new)
                .toList();
    }
}
