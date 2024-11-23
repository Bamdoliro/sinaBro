package com.bamdoliro.sinabro.application.letter;

import com.bamdoliro.sinabro.domain.character.domain.Character;
import com.bamdoliro.sinabro.domain.character.service.CharacterFacade;
import com.bamdoliro.sinabro.domain.diary.domain.type.Emotion;
import com.bamdoliro.sinabro.domain.letter.domain.Letter;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.ai.AIService;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.request.EmotionAndKeyword;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.response.GenerateLetterResponse;
import com.bamdoliro.sinabro.infrastructure.persistence.diary.DiaryRepository;
import com.bamdoliro.sinabro.infrastructure.persistence.letter.LetterRepository;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class GenerateLetterUseCase {

    private final LetterRepository letterRepository;
    private final DiaryRepository diaryRepository;
    private final AIService aiService;
    private final CharacterFacade characterFacade;

    public void execute(User user) {
        Character character = characterFacade.getCharacter(user);
        List<EmotionAndKeyword> emotionAndKeywords = extractEmotionAndKeywords(user);
        GenerateLetterResponse response = aiService.generateLetter(character.getType().getId(), character.getFriendship(), emotionAndKeywords);

        letterRepository.save(new Letter(response.getContent(), user));

        character.incrementFriendship();
    }

    private List<EmotionAndKeyword> extractEmotionAndKeywords(User user) {
        return diaryRepository.findAllByAuthorAndWrittenAtAfter(user, LocalDate.now().minusWeeks(1))
                .stream()
                .map(diary -> new EmotionAndKeyword(diary.getAnalyzedEmotion(), diary.getKeyword()))
                .toList();
    }
}
