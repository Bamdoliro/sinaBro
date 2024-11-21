package com.bamdoliro.sinabro.shared.util;

import com.bamdoliro.sinabro.application.auth.GoogleAuthLinkUseCase;
import com.bamdoliro.sinabro.application.auth.GoogleAuthUseCase;
import com.bamdoliro.sinabro.application.auth.LogOutUseCase;
import com.bamdoliro.sinabro.application.auth.RefreshAccessTokenUseCase;
import com.bamdoliro.sinabro.application.fcm.token.DeleteFCMTokenUseCase;
import com.bamdoliro.sinabro.application.fcm.token.SaveFCMTokenUseCase;
import com.bamdoliro.sinabro.application.notification.QueryNotificationListUseCase;
import com.bamdoliro.sinabro.application.notification.SendNotificationUseCase;
import com.bamdoliro.sinabro.domain.auth.service.TokenService;
import com.bamdoliro.sinabro.presentation.auth.AuthController;
import com.bamdoliro.sinabro.presentation.fcm.token.dto.FCMTokenController;
import com.bamdoliro.sinabro.presentation.notification.NotificationController;
import com.bamdoliro.sinabro.presentation.user.UserController;
import com.bamdoliro.sinabro.shared.auth.AuthenticationArgumentResolver;
import com.bamdoliro.sinabro.shared.auth.AuthenticationExtractor;
import com.bamdoliro.sinabro.shared.config.properties.JwtProperties;
import com.bamdoliro.sinabro.shared.response.SharedController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@Disabled
@WebMvcTest({
        AuthController.class,
        UserController.class,
        SharedController.class,
        NotificationController.class,
        FCMTokenController.class
})
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;


    // UseCase
    @MockBean
    protected GoogleAuthLinkUseCase googleAuthLinkUseCase;

    @MockBean
    protected GoogleAuthUseCase googleAuthUseCase;

    @MockBean
    protected RefreshAccessTokenUseCase refreshAccessTokenUseCase;

    @MockBean
    protected LogOutUseCase logOutUseCase;

    @MockBean
    protected SendNotificationUseCase sendNotificationUseCase;

    @MockBean
    protected QueryNotificationListUseCase queryNotificationListUseCase;

    @MockBean
    protected SaveFCMTokenUseCase saveFCMTokenUseCase;

    @MockBean
    protected DeleteFCMTokenUseCase deleteFCMTokenUseCase;

    // Service
    @MockBean
    protected TokenService tokenService;


    // Shared
    @MockBean
    protected JwtProperties jwtProperties;

    @MockBean
    protected AuthenticationArgumentResolver authenticationArgumentResolver;

    @MockBean
    protected AuthenticationExtractor authenticationExtractor;

    protected String toJson(Object object) throws JsonProcessingException, JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
