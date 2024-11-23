package com.bamdoliro.sinabro.domain.diary.domain.type;

import com.bamdoliro.sinabro.shared.enumeration.EnumProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Emotion implements EnumProperty {
    PROUD("뿌듯한", Category.JOYFUL),
    FRUITFUL("보람찬", Category.JOYFUL),
    ECSTATIC("황홀한", Category.JOYFUL),

    DISAPPOINTED("섭섭한", Category.SAD),
    UPSET("속상한", Category.SAD),
    REGRETFUL("아쉬운", Category.SAD),

    IMPRESSED("감탄한", Category.SURPRISED),
    AMAZED("놀라운", Category.SURPRISED),
    ASTOUNDED("경악한", Category.SURPRISED),

    EXCITED("설레는", Category.ROMANTIC),
    POUNDING("두근거리는", Category.ROMANTIC),
    DEDICATED("헌신적인", Category.ROMANTIC),

    GLOOMY("시무룩한", Category.DEPRESSED),
    HOPELESS("절망스러운", Category.DEPRESSED),
    VAIN("허무한", Category.DEPRESSED),

    PEACEFUL("평화로운", Category.STABLE),
    COMFORTABLE("안락한", Category.STABLE),
    CALM("안정된", Category.STABLE),

    EMBARRASSED("당혹스러운", Category.EMBARRASSED),
    BITTER("떨떠름한", Category.EMBARRASSED),
    PUZZELD("의아한", Category.EMBARRASSED),

    IRRITATED("짜증나는", Category.ANGRY),
    FURIOUS("분한", Category.ANGRY),
    ENRAGED("울화통 터지는", Category.ANGRY);

    private final String description;
    private final Category category;

    @Getter
    @RequiredArgsConstructor
    public enum Category implements EnumProperty {
        JOYFUL("기쁜"),
        SAD("슬픈"),
        SURPRISED("놀란"),
        ROMANTIC("사랑"),
        DEPRESSED("우울한"),
        STABLE("평범한"),
        EMBARRASSED("당황스러운"),
        ANGRY("화난");

        private final String description;
    }
}
