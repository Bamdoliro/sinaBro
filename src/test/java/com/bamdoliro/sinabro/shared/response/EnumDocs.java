package com.bamdoliro.sinabro.shared.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnumDocs {

    Map<String, String> authority;
    Map<String, String> emotion;
    Map<String, String> emotionCategory;
    Map<String, String> characterType;
    Map<String, String> inquiryStatus;
}
