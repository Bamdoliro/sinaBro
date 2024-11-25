package com.bamdoliro.sinabro.presentation.answer;

import com.bamdoliro.sinabro.application.answer.CreateAnswerUseCase;
import com.bamdoliro.sinabro.application.answer.UpdateAnswerUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.answer.dto.request.AnswerRequest;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.auth.Authority;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/answers")
@RestController
public class AnswerController {

    private final CreateAnswerUseCase createAnswerUseCase;
    private final UpdateAnswerUseCase updateAnswerUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{inquiry-id}")
    public IdResponse createAnswer(
            @AuthenticationPrincipal(authority = Authority.ADMIN) User user,
            @PathVariable(name = "inquiry-id") Long inquiryId,
            @RequestBody @Valid AnswerRequest request
    ) {
        return createAnswerUseCase.execute(user, inquiryId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{answer-id}")
    public void updateAnswer(
            @AuthenticationPrincipal(authority = Authority.ADMIN) User user,
            @PathVariable(name = "answer-id") Long answerId,
            @RequestBody @Valid AnswerRequest request
    ) {
        updateAnswerUseCase.execute(user, answerId, request);
    }
}
