package com.bamdoliro.sinabro.shared.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("auth")
public class AuthProperties {

    private OAuth google;

    @Getter
    @Setter
    public static class OAuth {
        private String baseUrl;
        private String clientId;
        private String clientSecret;
        private String redirectUri;
    }

    public String getGoogleBaseUrl() {
        return google.getBaseUrl();
    }

    public String getGoogleClientId() {
        return google.getClientId();
    }

    public String getGoogleClientSecret() {
        return google.getClientSecret();
    }

    public String getGoogleRedirectUri() {
        return google.getRedirectUri();
    }
}
