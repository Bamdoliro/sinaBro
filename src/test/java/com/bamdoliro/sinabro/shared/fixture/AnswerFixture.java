package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.answer.domain.Answer;
import com.bamdoliro.sinabro.presentation.answer.dto.request.AnswerRequest;

public class AnswerFixture {

    public static Answer createAnswer() {
        return new Answer(
                "저희의 미흡했던 점을 알려주셔서 감사합니다. 편지의 말투는 현재 수정 주이며 곧 안정화된 편지를 받아보실 수 있을 겁니다.",
                InquiryFixture.createInquiry(),
                UserFixture.createAdmin()
        );
    }

    public static AnswerRequest createAnswerRequest() {
        return new AnswerRequest(
                "저희의 미흡했던 점을 알려주셔서 감사합니다. 편지의 말투는 현재 수정 주이며 곧 안정화된 편지를 받아보실 수 있을 겁니다."
        );
    }
}
