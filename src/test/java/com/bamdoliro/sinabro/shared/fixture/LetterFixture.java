package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.letter.domain.Letter;
import com.bamdoliro.sinabro.presentation.letter.dto.response.LetterResponse;
import com.bamdoliro.sinabro.presentation.letter.dto.response.ListLetterResponse;

public class LetterFixture {

    public static Letter createLetter() {
        return new Letter(
                "안녕 난 헌이라고 해. 헌은 어디서 왔냐고? 나도 몰라~\n너무 좋았어. 다음에 편지 보낼게^^",
                UserFixture.createUser()
        );
    }

    public static LetterResponse createLetterResponse() { return new LetterResponse(createLetter()); }

    public static ListLetterResponse createListDiaryResponse() { return new ListLetterResponse(createLetter()); }
}
