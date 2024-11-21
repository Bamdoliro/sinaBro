package com.bamdoliro.sinabro.shared.fixture;

import com.bamdoliro.sinabro.shared.response.IdResponse;

public class SharedFixture {

    public static IdResponse createIdResponse() {
        return new IdResponse(1L);
    }
}
