package com.bamdoliro.sinabro.infrastructure.feign.auth.google;

import com.bamdoliro.sinabro.infrastructure.feign.auth.google.dto.response.GoogleInformationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GoogleInformationClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
public interface GoogleInformationClient {

    @GetMapping("?alt=json")
    public GoogleInformationResponse getUserInformation(@RequestHeader("Authorization") String accessToken);
}
