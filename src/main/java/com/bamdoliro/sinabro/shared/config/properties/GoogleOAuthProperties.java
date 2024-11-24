package com.bamdoliro.sinabro.shared.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("auth")
public class GoogleOAuthProperties {

    private OAuth google;

    @Getter
    @Setter
    public static class OAuth {
        private String baseUrl;
        private String clientId;
        private String clientSecret;
        private String redirectUri;
    }
}
