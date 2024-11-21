package com.bamdoliro.sinabro.presentation.diary;

import com.bamdoliro.sinabro.application.diary.CreateDiaryUseCase;
import com.bamdoliro.sinabro.application.diary.DeleteDiaryUseCase;
import com.bamdoliro.sinabro.application.diary.GetAllDiaryUseCase;
import com.bamdoliro.sinabro.application.diary.UpdateDiaryUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.persistence.diary.DiaryRepository;
import com.bamdoliro.sinabro.presentation.diary.dto.request.DiaryRequest;
import com.bamdoliro.sinabro.presentation.diary.dto.response.SimpleDiaryResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import com.bamdoliro.sinabro.shared.response.ListCommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/diaries")
@RestController
public class DiaryController {

    private final CreateDiaryUseCase createDiaryUseCase;
    private final UpdateDiaryUseCase updateDiaryUseCase;
    private final DeleteDiaryUseCase deleteDiaryUseCase;
    private final GetAllDiaryUseCase getAllDiaryUseCase;
    private final DiaryRepository diaryRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SingleCommonResponse<IdResponse> createDiary(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid DiaryRequest request
    ) {
        return SingleCommonResponse.ok(
                createDiaryUseCase.execute(user, request)
        );
    }

    @GetMapping
    public ListCommonResponse<SimpleDiaryResponse> getAllDiary(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) LocalDateTime startDate,
            @RequestParam(required = false) LocalDateTime endDate
    ) {
        return ListCommonResponse.ok(
            getAllDiaryUseCase.execute(user, startDate, endDate)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{diary-id}")
    public void updateDiary(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "diary-id") Long diaryId,
            @RequestBody @Valid DiaryRequest request
    ) {
        updateDiaryUseCase.execute(user, diaryId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{diary-id}")
    public void deleteDiary(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "diary-id") Long diaryId
    ) {
        deleteDiaryUseCase.execute(user, diaryId);
    }
}
