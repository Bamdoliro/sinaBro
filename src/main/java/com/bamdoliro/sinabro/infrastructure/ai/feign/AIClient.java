package com.bamdoliro.sinabro.infrastructure.ai.feign;

import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.request.AnalyzeDiaryRequest;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.request.GenerateLetterRequest;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.response.AnalyzeDiaryResponse;
import com.bamdoliro.sinabro.infrastructure.ai.feign.dto.response.GenerateLetterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AIClient", url = "http://43.201.91.78:8000/api/v1")
public interface AIClient {

    @PostMapping("/analyze/diary")
    AnalyzeDiaryResponse analyzeDiary(@RequestBody AnalyzeDiaryRequest request);

    @PostMapping("/generate/letter")
    GenerateLetterResponse generateLetter(@RequestBody GenerateLetterRequest request);
}
