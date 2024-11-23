package com.bamdoliro.sinabro.presentation.question;

import com.bamdoliro.sinabro.application.question.*;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.question.dto.request.QuestionRequest;
import com.bamdoliro.sinabro.presentation.question.dto.response.ListQuestionResponse;
import com.bamdoliro.sinabro.presentation.question.dto.response.QuestionResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.auth.Authority;
import com.bamdoliro.sinabro.shared.response.CommonResponse;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import com.bamdoliro.sinabro.shared.response.ListCommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/questions")
@RestController
public class QuestionController {

    private final CreateQuestionUseCase createQuestionUseCase;
    private final GetAllQuestionUseCase getAllQuestionUseCase;
    private final GetQuestionUseCase getQuestionUseCase;
    private final UpdateQuestionUseCase updateQuestionUseCase;
    private final DeleteQuestionUseCase deleteQuestionUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SingleCommonResponse<IdResponse> createQuestion(
            @AuthenticationPrincipal(authority = Authority.ADMIN) User user,
            @RequestBody @Valid QuestionRequest request
    ) {
        return CommonResponse.ok(
                createQuestionUseCase.execute(request)
        );
    }

    @GetMapping
    public ListCommonResponse<ListQuestionResponse> getAllQuestion() {
        return CommonResponse.ok(
                getAllQuestionUseCase.execute()
        );
    }

    @GetMapping("/{question-id}")
    public SingleCommonResponse<QuestionResponse> getQuestion(
            @PathVariable(name = "question-id") Long questionId
    ) {
        return CommonResponse.ok(
                getQuestionUseCase.execute(questionId)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{question-id}")
    public void updateQuestion(
            @AuthenticationPrincipal(authority = Authority.ADMIN) User user,
            @PathVariable(name = "question-id") Long questionId,
            @RequestBody @Valid QuestionRequest request
    ) {
        updateQuestionUseCase.execute(questionId, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{question-id}")
    public void deleteQuestion(
            @AuthenticationPrincipal(authority = Authority.ADMIN) User user,
            @PathVariable(name = "question-id") Long questionId
    ) {
        deleteQuestionUseCase.execute(questionId);
    }
}
