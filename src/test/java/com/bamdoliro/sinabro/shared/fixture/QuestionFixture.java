package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.presentation.question.dto.request.QuestionRequest;
import com.bamdoliro.sinabro.presentation.question.dto.response.ListQuestionResponse;
import com.bamdoliro.sinabro.presentation.question.dto.response.QuestionResponse;

import java.time.LocalDateTime;

public class QuestionFixture {

    public static QuestionRequest createQuestionRequest() {
        return new QuestionRequest(
                "캐릭터 편지 몇일에 한번씩 발송되나요?",
                "캐릭터들의 편지는 보통 1일~3일 내에 1통 발송됩니다."
        );
    }

    public static ListQuestionResponse createListQuestionResponse() {
        return new ListQuestionResponse(
                1L,
                "캐릭터 편지 몇일에 한번씩 발송되나요?",
                "캐릭터들의 편지는 보통 1일~3일 내에 1통 발송됩니다.",
                LocalDateTime.now()
        );
    }

    public static QuestionResponse createQuestionResponse() {
        return new QuestionResponse(
                1L,
                "캐릭터 편지 몇일에 한번씩 발송되나요?",
                "캐릭터들의 편지는 보통 1일~3일 내에 1통 발송됩니다.",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
