package com.bamdoliro.sinabro.application.diary;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.diary.DiaryRepository;
import com.bamdoliro.sinabro.presentation.diary.dto.request.DiaryRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class CreateDiaryUseCase {

    private final DiaryRepository diaryRepository;

    public IdResponse execute(User user, DiaryRequest request) {
        Diary diary = diaryRepository.save(
                new Diary(request.getContent(), request.getEmotionList(), user)
        );

        return new IdResponse(diary);
    }
}
