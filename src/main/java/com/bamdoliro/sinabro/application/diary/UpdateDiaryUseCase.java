package com.bamdoliro.sinabro.application.diary;

import com.bamdoliro.sinabro.domain.auth.exception.AuthorityMismatchException;
import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.diary.service.DiaryFacade;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.ai.AIService;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.response.AnalyzeDiaryResponse;
import com.bamdoliro.sinabro.presentation.diary.dto.request.UpdateDiaryRequest;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class UpdateDiaryUseCase {

    private final DiaryFacade diaryFacade;
    private final AIService aiService;

    @Transactional
    public void execute(User user, Long id, UpdateDiaryRequest request) {
        Diary diary = diaryFacade.getDiary(id);
        validate(user, diary);

        AnalyzeDiaryResponse analyzeResult = aiService.analyzeDiary(request.getContent());

        diary.update(
                request.getContent(),
                request.getEmotionList(),
                analyzeResult.getEmotion(),
                analyzeResult.getKeyword()
        );
    }

    private void validate(User user, Diary diary) {
        if (!diary.isAuthor(user)) {
            throw new AuthorityMismatchException();
        }
    }
}
