package com.bamdoliro.sinabro.presentation.character;

import com.bamdoliro.sinabro.domain.character.domain.type.CharacterType;
import com.bamdoliro.sinabro.domain.user.domain.User;
import com.bamdoliro.sinabro.presentation.character.dto.request.SelectCharacterRequest;
import com.bamdoliro.sinabro.shared.fixture.AuthFixture;
import com.bamdoliro.sinabro.shared.fixture.UserFixture;
import com.bamdoliro.sinabro.shared.util.RestDocsTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CharacterControllerTest extends RestDocsTestSupport {

    @Test
    void 유저가_캐릭터를_선택한다() throws Exception {
        SelectCharacterRequest request = new SelectCharacterRequest(CharacterType.HEON);
        User user = UserFixture.createUser();

        given(authenticationArgumentResolver.supportsParameter(any(MethodParameter.class))).willReturn(true);
        given(authenticationArgumentResolver.resolveArgument(any(), any(), any(), any())).willReturn(user);
        willDoNothing().given(selectCharacterUseCase).execute(user, request);

        mockMvc.perform(post("/characters")
                        .header(HttpHeaders.AUTHORIZATION, AuthFixture.createAuthHeader())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(request))
                )

                .andExpect(status().isCreated())

                .andDo(restDocs.document(
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION)
                                        .description("Bearer token")
                        ),
                        requestFields(
                                fieldWithPath("type")
                                        .type(JsonFieldType.STRING)
                                        .description("<<character-type,캐릭터 유형>>")
                        )
                ));
    }
}
