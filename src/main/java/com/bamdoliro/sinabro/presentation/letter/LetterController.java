package com.bamdoliro.sinabro.presentation.letter;

import com.bamdoliro.sinabro.application.letter.GenerateLetterUseCase;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.shared.auth.AuthenticationPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/letters")
@RestController
public class LetterController {

    private final GenerateLetterUseCase generateLetterUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void generateLetter(
            @AuthenticationPrincipal User user
    ) {
        generateLetterUseCase.execute(user);
    }
}
