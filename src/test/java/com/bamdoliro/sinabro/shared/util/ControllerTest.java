package com.bamdoliro.sinabro.shared.util;

import com.bamdoliro.sinabro.application.auth.GoogleAuthLinkUseCase;
import com.bamdoliro.sinabro.application.auth.LogOutUseCase;
import com.bamdoliro.sinabro.application.auth.RefreshAccessTokenUseCase;
import com.bamdoliro.sinabro.application.auth.*;
import com.bamdoliro.sinabro.application.character.GetCharacterUseCase;
import com.bamdoliro.sinabro.application.diary.*;
import com.bamdoliro.sinabro.application.notification.SendNotificationToAllUserUseCase;
import com.bamdoliro.sinabro.application.question.*;
import com.bamdoliro.sinabro.domain.auth.service.TokenService;
import com.bamdoliro.sinabro.presentation.auth.AuthController;
import com.bamdoliro.sinabro.presentation.diary.DiaryController;
import com.bamdoliro.sinabro.application.character.SelectCharacterUseCase;
import com.bamdoliro.sinabro.application.fcm.token.DeleteFCMTokenUseCase;
import com.bamdoliro.sinabro.application.fcm.token.SaveFCMTokenUseCase;
import com.bamdoliro.sinabro.application.letter.GenerateLetterUseCase;
import com.bamdoliro.sinabro.application.letter.GetAllLetterUseCase;
import com.bamdoliro.sinabro.application.letter.GetLetterUseCase;
import com.bamdoliro.sinabro.application.notification.QueryNotificationListUseCase;
import com.bamdoliro.sinabro.application.notification.SendNotificationUseCase;
import com.bamdoliro.sinabro.presentation.character.CharacterController;
import com.bamdoliro.sinabro.presentation.fcm.token.FCMTokenController;
import com.bamdoliro.sinabro.presentation.letter.LetterController;
import com.bamdoliro.sinabro.presentation.notification.NotificationController;
import com.bamdoliro.sinabro.presentation.question.QuestionController;
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
        DiaryController.class,
        NotificationController.class,
        FCMTokenController.class,
        CharacterController.class,
        QuestionController.class,
        LetterController.class,
        SharedController.class
})
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;


    // UseCase

    // Auth
    @MockBean
    protected GoogleAuthLinkUseCase googleAuthLinkUseCase;

    @MockBean
    protected GetGoogleAccessTokenUseCase getGoogleAccessTokenUseCase;

    @MockBean
    protected GoogleAuthWebUseCase googleAuthWebUseCase;

    @MockBean
    protected GoogleAuthAppUseCase googleAuthAppUseCase;

    @MockBean
    protected RefreshAccessTokenUseCase refreshAccessTokenUseCase;

    @MockBean
    protected LogOutUseCase logOutUseCase;

    // Diary
    @MockBean
    protected CreateDiaryUseCase createDiaryUseCase;

    @MockBean
    protected GetAllDiaryUseCase getAllDiaryUseCase;

    @MockBean
    protected GetDiaryUseCase getDiaryUseCase;

    @MockBean
    protected UpdateDiaryUseCase updateDiaryUseCase;

    @MockBean
    protected DeleteDiaryUseCase deleteDiaryUseCase;

    // Notification
    @MockBean
    protected SendNotificationUseCase sendNotificationUseCase;

    @MockBean
    protected QueryNotificationListUseCase queryNotificationListUseCase;

    @MockBean
    protected SendNotificationToAllUserUseCase sendNotificationToAllUserUseCase;

    @MockBean
    protected SaveFCMTokenUseCase saveFCMTokenUseCase;

    @MockBean
    protected DeleteFCMTokenUseCase deleteFCMTokenUseCase;

    // Character
    @MockBean
    protected SelectCharacterUseCase selectCharacterUseCase;

    // Question
    @MockBean
    protected CreateQuestionUseCase createQuestionUseCase;

    @MockBean
    protected GetAllQuestionUseCase getAllQuestionUseCase;

    @MockBean
    protected GetQuestionUseCase getQuestionUseCase;

    @MockBean
    protected UpdateQuestionUseCase updateQuestionUseCase;

    @MockBean
    protected DeleteQuestionUseCase deleteQuestionUseCase;

    @MockBean
    protected GetCharacterUseCase getCharacterUseCase;

    // Letter
    @MockBean
    protected GenerateLetterUseCase generateLetterUseCase;

    @MockBean
    protected GetAllLetterUseCase getAllLetterUseCase;

    @MockBean
    protected GetLetterUseCase getLetterUseCase;

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


    protected String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
