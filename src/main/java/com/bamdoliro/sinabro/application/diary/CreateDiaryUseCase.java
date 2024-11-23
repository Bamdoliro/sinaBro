package com.bamdoliro.sinabro.application.diary;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.diary.exception.DiaryAlreadyWrittenException;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.ai.AIService;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.response.AnalyzeDiaryResponse;
import com.bamdoliro.sinabro.infrastructure.persistence.diary.DiaryRepository;
import com.bamdoliro.sinabro.presentation.diary.dto.request.DiaryRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class CreateDiaryUseCase {

    private final DiaryRepository diaryRepository;
    private final AIService aiService;

    @Transactional
    public IdResponse execute(User user, DiaryRequest request) {
        validate(user, request);

        AnalyzeDiaryResponse response = aiService.analyzeDiary(request.getContent());
        Diary diary = Diary.builder()
                .content(request.getContent())
                .emotionList(request.getEmotionList())
                .analyzedEmotion(response.getEmotion())
                .keyword(response.getKeyword())
                .writtenAt(request.getWrittenAt())
                .author(user)
                .build();

        Diary savedDiary = diaryRepository.save(diary);

        return new IdResponse(savedDiary);
    }

    private void validate(User user, DiaryRequest request) {
        if (diaryRepository.existsByAuthorAndWrittenAt(user, request.getWrittenAt())) {
            throw new DiaryAlreadyWrittenException();
        }
    }
}
