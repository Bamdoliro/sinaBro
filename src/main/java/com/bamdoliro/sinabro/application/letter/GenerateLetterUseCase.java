package com.bamdoliro.sinabro.application.letter;

import com.bamdoliro.sinabro.domain.character.domain.Character;
import com.bamdoliro.sinabro.domain.character.service.CharacterFacade;
import com.bamdoliro.sinabro.domain.diary.service.DiaryFacade;
import com.bamdoliro.sinabro.domain.letter.domain.Letter;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.ai.AIService;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.request.EmotionAndKeyword;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.response.GenerateLetterResponse;
import com.bamdoliro.sinabro.infrastructure.persistence.diary.DiaryRepository;
import com.bamdoliro.sinabro.infrastructure.persistence.letter.LetterRepository;
import com.bamdoliro.sinabro.shared.annotation.UseCase;
import com.bamdoliro.sinabro.shared.response.IdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class GenerateLetterUseCase {

    private final LetterRepository letterRepository;
    private final DiaryRepository diaryRepository;
    private final AIService aiService;
    private final CharacterFacade characterFacade;
    private final DiaryFacade diaryFacade;

    @Transactional
    public IdResponse execute(User user) {
        Character character = characterFacade.getCharacter(user);
        List<EmotionAndKeyword> emotionAndKeywords = extractEmotionAndKeywords(user);
        GenerateLetterResponse response = aiService.generateLetter(character.getType().getId(), character.getFriendship(), emotionAndKeywords);

        Letter letter = letterRepository.save(new Letter(response.getContent(), user));

        character.increaseFriendShip();

        return new IdResponse(letter);
    }

    private List<EmotionAndKeyword> extractEmotionAndKeywords(User user) {
//        return diaryRepository.findAllByAuthorAndWrittenAtAfter(user, LocalDate.now().minusWeeks(1))
//                .stream()
//                .map(EmotionAndKeyword::new)
//                .toList();

        return List.of(
                new EmotionAndKeyword(diaryFacade.getCurrentDiary(user))
        );
    }
}
