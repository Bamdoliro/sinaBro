package com.bamdoliro.sinabro.domain.diary.service;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.diary.exception.DiaryNotFoundException;
import com.bamdoliro.sinabro.infrastructure.persistence.diary.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class DiaryFacade {

    private final DiaryRepository diaryRepository;

    @Transactional(readOnly = true)
    public Diary getDiary(Long id) {
        return diaryRepository.findById(id)
                .orElseThrow(DiaryNotFoundException::new);
    }
}
