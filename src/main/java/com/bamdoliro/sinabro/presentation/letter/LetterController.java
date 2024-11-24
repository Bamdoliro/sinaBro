package com.bamdoliro.sinabro.presentation.letter;

import com.bamdoliro.sinabro.application.letter.GenerateLetterUseCase;
import com.bamdoliro.sinabro.application.letter.GetAllLetterUseCase;
import com.bamdoliro.sinabro.application.letter.GetLetterUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.letter.dto.response.LetterResponse;
import com.bamdoliro.sinabro.presentation.letter.dto.response.ListLetterResponse;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import com.bamdoliro.sinabro.shared.response.ListCommonResponse;
import com.bamdoliro.sinabro.shared.response.SingleCommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/letters")
@RestController
public class LetterController {

    private final GenerateLetterUseCase generateLetterUseCase;
    private final GetAllLetterUseCase getAllLetterUseCase;
    private final GetLetterUseCase getLetterUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public IdResponse generateLetter(
            @AuthenticationPrincipal User user
    ) {
        return generateLetterUseCase.execute(user);
    }

    @GetMapping
    public ListCommonResponse<ListLetterResponse> getAllLetter(
            @AuthenticationPrincipal User user
    ) {
        return ListCommonResponse.ok(
          getAllLetterUseCase.execute(user)
        );
    }

    @GetMapping("/{letter-id}")
    public SingleCommonResponse<LetterResponse> getLetter(
            @AuthenticationPrincipal User user,
            @PathVariable(name = "letter-id") Long letterId
    ) {
        return SingleCommonResponse.ok(
                getLetterUseCase.execute(user, letterId)
        );
    }
}
