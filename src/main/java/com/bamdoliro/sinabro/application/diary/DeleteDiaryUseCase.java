package com.bamdoliro.sinabro.application.diary;

import com.bamdoliro.sinabro.domain.auth.exception.AuthorityMismatchException;
import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.diary.service.DiaryFacade;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.diary.DiaryRepository;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class DeleteDiaryUseCase {

    private final DiaryFacade diaryFacade;
    private final DiaryRepository diaryRepository;

    public void execute(User user, Long id) {
        Diary diary = diaryFacade.getDiary(id);
        validate(user, diary);

        diaryRepository.delete(diary);
    }

    private void validate(User user, Diary diary) {
        if (!diary.isAuthor(user)) {
            throw new AuthorityMismatchException();
        }
    }
}
