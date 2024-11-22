package com.bamdoliro.sinabro.infrastructure.oauth.google.feign;

import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.request.GoogleAuthRequest;
import com.bamdoliro.sinabro.infrastructure.oauth.google.feign.dto.response.GoogleTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "GoogleAuthClient", url = "https://oauth2.googleapis.com/token")
public interface GoogleAuthClient {

    @PostMapping
    GoogleTokenResponse getAccessToken(GoogleAuthRequest request);
}
