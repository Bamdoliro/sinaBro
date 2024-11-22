package com.bamdoliro.sinabro.infrastructure.oauth.google.feign;

import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.response.GoogleInformationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GoogleInformationClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
public interface GoogleInformationClient {

    @GetMapping("?alt=json")
    GoogleInformationResponse getUserInformation(@RequestHeader("Authorization") String accessToken);
}
