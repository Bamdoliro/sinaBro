package com.bamdoliro.sinabro.infrastructure.ai;

import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.infrastructure.ai.feign.AIClient;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.request.AnalyzeDiaryRequest;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.request.EmotionAndKeyword;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.request.GenerateLetterRequest;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.response.AnalyzeDiaryResponse;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.response.GenerateLetterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AIService {

    private final AIClient aiClient;

    public AnalyzeDiaryResponse analyzeDiary(String content) {
        return aiClient.analyzeDiary(new AnalyzeDiaryRequest(content));
    }

    public GenerateLetterResponse generateLetter(Integer characterTypeId, Integer friendShip, List<EmotionAndKeyword> emotionAndKeywordList) {
        return aiClient.generateLetter(new GenerateLetterRequest(characterTypeId, friendShip, emotionAndKeywordList));
    }
}
