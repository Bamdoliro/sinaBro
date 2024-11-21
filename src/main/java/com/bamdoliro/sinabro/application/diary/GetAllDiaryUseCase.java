package com.bamdoliro.sinabro.application.diary;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.diary.DiaryRepository;
import com.bamdoliro.sinabro.presentation.diary.dto.response.SimpleDiaryResponse;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class GetAllDiaryUseCase {

    private final DiaryRepository diaryRepository;

    public List<SimpleDiaryResponse> execute(User user, LocalDateTime startDate, LocalDateTime endDate) {
        List<Diary> diaryList;
        if (startDate != null && endDate != null) {
            diaryList = diaryRepository.findAllByAuthorAndCreatedAtBetween(user, startDate, endDate);
        } else if (startDate != null) {
            diaryList = diaryRepository.findAllByAuthorAndCreatedAtAfter(user, startDate);
        } else if (endDate != null) {
            diaryList = diaryRepository.findAllByAuthorAndCreatedAtBefore(user, endDate);
        } else {
            diaryList = diaryRepository.findAllByAuthor(user);
        }

         return diaryList.stream()
                .map(SimpleDiaryResponse::new)
                .toList();
    }
}
