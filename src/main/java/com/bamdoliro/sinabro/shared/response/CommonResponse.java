package com.bamdoliro.sinabro.shared.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CommonResponse {
    String code;
    String message;

    public static <T> SingleCommonResponse<T> success(T data) {
        return new SingleCommonResponse<>(
                "OK",
                "ok",
                data
        );
    }

    public static <T> ListCommonResponse<T> success(List<T> dataList) {
        return new ListCommonResponse<>(
                "OK",
                "ok",
                dataList
        );
    }
}
