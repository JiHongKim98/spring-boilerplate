package com.example.oauth2.auth.presentation;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.example.oauth2.auth.application.dto.TokenResponse;
import com.example.oauth2.common.ControllerTest;

@WebMvcTest(AuthController.class)
class AuthControllerTest extends ControllerTest {

	private static final String PROVIDER_GOOGLE = "google";
	private static final String CODE = "code";

	@Test
	@DisplayName("인증 토큰을 발급한다.")
	void loginTest() throws Exception {
		// given
		TokenResponse tokenResponse = TokenResponse.of("eyJhb.9bGLEdZ.pas-Ya");
		when(authService.loginOrCreateMember(anyString(), anyString()))
			.thenReturn(tokenResponse);

		// when
		ResultActions action = mockMvc.perform(get("/api/v1/oauth/{provider}/callback", PROVIDER_GOOGLE)
			.param(CODE, "AuthorizationCode")
		);

		// then
		action
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(tokenResponse)))

			// Docs
			.andDo(document(
				"identifier",
				resource(ResourceSnippetParameters.builder()
					.tag("Auth")
					.summary("OAuth login")
					.description("OAuth 제공자로부터 받은 code를 기반으로 인증 토큰을 발급 받음.")
					.pathParameters(
						parameterWithName("provider").description("OAuth 제공자 이름")
					).queryParameters(
						parameterWithName(CODE).description("OAuth 제공자 받은 code")
					).responseFields(
						fieldWithPath("access_token").description("인증 토큰")
					).responseSchema(Schema.schema("TokenResponse"))
					.build())
			));
	}
}
