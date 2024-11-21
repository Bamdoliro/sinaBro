package com.bamdoliro.sinabro.presentation.diary;

import com.bamdoliro.sinabro.application.diary.*;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.diary.dto.request.DiaryRequest;
import com.bamdoliro.sinabro.presentation.diary.dto.request.UpdateDiaryRequest;
import com.bamdoliro.sinabro.presentation.diary.dto.response.DiaryResponse;
import com.bamdoliro.sinabro.presentation.diary.dto.response.ListDiaryResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import com.bamdoliro.sinabro.shared.response.ListCommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RequestMapping("/diaries")
@RestController
public class DiaryController {

    private final CreateDiaryUseCase createDiaryUseCase;
    private final UpdateDiaryUseCase updateDiaryUseCase;
    private final DeleteDiaryUseCase deleteDiaryUseCase;
    private final GetAllDiaryUseCase getAllDiaryUseCase;
    private final GetDiaryUseCase getDiaryUseCase;

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
    public ListCommonResponse<ListDiaryResponse> getAllDiary(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate
    ) {
        return ListCommonResponse.ok(
            getAllDiaryUseCase.execute(user, startDate, endDate)
        );
    }

    @GetMapping("/{diary-id}")
    public SingleCommonResponse<DiaryResponse> getDiary(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "diary-id") Long diaryId
    ) {
        return SingleCommonResponse.ok(
                getDiaryUseCase.execute(user, diaryId)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{diary-id}")
    public void updateDiary(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "diary-id") Long diaryId,
            @RequestBody @Valid UpdateDiaryRequest request
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
