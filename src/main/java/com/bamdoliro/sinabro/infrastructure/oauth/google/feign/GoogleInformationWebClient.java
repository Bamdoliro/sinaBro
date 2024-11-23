package com.bamdoliro.sinabro.infrastructure.oauth.google.feign;

import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.response.GoogleInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GoogleInformationWebClient", url = "https://www.googleapis.com/oauth2/v1/userinfo")
public interface GoogleInformationWebClient {

    @GetMapping("?alt=json")
    GoogleInformation getUserInformation(@RequestHeader("Authorization") String accessToken);
}
