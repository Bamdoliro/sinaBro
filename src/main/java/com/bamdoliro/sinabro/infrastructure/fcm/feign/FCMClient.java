package com.bamdoliro.sinabro.infrastructure.fcm.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "FCMClient", url = "https://fcm.googleapis.com/v1/projects/sinabro-441415/messages:send")
public interface FCMClient {

    @PostMapping
    void sendMessage(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
            @RequestBody String message
    );
}
