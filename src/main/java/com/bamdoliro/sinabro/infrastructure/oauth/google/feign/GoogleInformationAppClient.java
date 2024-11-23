package com.bamdoliro.sinabro.infrastructure.oauth.google.feign;

import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.response.GoogleInformation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GoogleInformationAppClient", url = "https://oauth2.googleapis.com/tokeninfo")
public interface GoogleInformationAppClient {

    @GetMapping
    GoogleInformation getUserInformation(@RequestParam(name = "id_token") String id_token);
}
