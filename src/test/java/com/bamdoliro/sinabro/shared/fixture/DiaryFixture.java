package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.domain.diary.domain.Diary;
import com.bamdoliro.sinabro.domain.diary.domain.type.Emotion;
import com.bamdoliro.sinabro.presentation.diary.dto.request.DiaryRequest;
import com.bamdoliro.sinabro.presentation.diary.dto.request.UpdateDiaryRequest;
import com.bamdoliro.sinabro.presentation.diary.dto.response.DiaryResponse;
import com.bamdoliro.sinabro.presentation.diary.dto.response.ListDiaryResponse;

import java.time.LocalDate;
import java.util.List;

public class DiaryFixture {

    public static Diary createDiary() {
        return new Diary(
        "오늘은 네트워크 수업을 했다. WireShark를 통해서 패킷을 직접 분석해보니까 재밌었다.\n" +
                "그런데 C++ 알고리즘 시간이 진짜 어려웠다. 그래프 너무 힘들다. 도와줘요 창엽쌤",
                List.of(
                        Emotion.FRUITFUL,
                        Emotion.CALM,
                        Emotion.GLOOMY
                ),
                LocalDate.now(),
                UserFixture.createUser()
        );
    }

    public static DiaryRequest createDiaryRequest() {
        return new DiaryRequest(
                "오늘은 네트워크 수업을 했다. WireShark를 통해서 패킷을 직접 분석해보니까 재밌었다.\n" +
                "그런데 C++ 알고리즘 시간이 진짜 어려웠다. 그래프 너무 힘들다. 도와줘요 창엽쌤",
                List.of(
                        Emotion.FRUITFUL,
                        Emotion.CALM,
                        Emotion.GLOOMY
                ),
                LocalDate.now()
        );
    }

    public static UpdateDiaryRequest createUpdateDiaryRequest() {
        return new UpdateDiaryRequest(
                "오늘은 네트워크 수업을 했다. WireShark를 통해서 패킷을 직접 분석해보니까 재밌었다.\n" +
                        "그런데 C++ 알고리즘 시간이 진짜 어려웠다. 그래프 너무 힘들다. 도와줘요 창엽쌤",
                List.of(
                        Emotion.FRUITFUL,
                        Emotion.CALM,
                        Emotion.GLOOMY
                )
        );
    }

    public static DiaryResponse createDiaryResponse() {
        return new DiaryResponse(createDiary());
    }

    public static ListDiaryResponse createListDiaryResponse() {
        return new ListDiaryResponse(createDiary());
    }
}
