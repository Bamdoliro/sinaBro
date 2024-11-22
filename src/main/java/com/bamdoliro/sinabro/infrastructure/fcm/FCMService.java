package com.bamdoliro.sinabro.infrastructure.fcm;

import com.bamdoliro.sinabro.infrastructure.fcm.exception.FailedToSendException;
import com.bamdoliro.sinabro.infrastructure.fcm.feign.FCMClient;
import com.bamdoliro.sinabro.infrastructure.fcm.feign.dto.request.SendFCMRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class FCMService {

    private final FCMClient fcmClient;

    public void sendMessageTo(String token, String title, String body) {
        try {
            String message = makeMessage(token, title, body);
            fcmClient.sendMessage("Bearer " + getAccessToken(), message);
        } catch (Exception e) {
            throw new FailedToSendException();
        }
    }

    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "config/fcm-setting.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped("https://www.googleapis.com/auth/firebase.messaging", "https://www.googleapis.com/auth/cloud-platform");

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    private String makeMessage(String token, String title, String body) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SendFCMRequest request = SendFCMRequest.builder()
                .message(SendFCMRequest.Message.builder()
                        .token(token)
                        .notification(SendFCMRequest.Notification.builder()
                                .title(title)
                                .body(body)
                                .image(null)
                                .build()
                        )
                        .build()
                )
                .validateOnly(false)
                .build();

        return mapper.writeValueAsString(request);
    }
}


