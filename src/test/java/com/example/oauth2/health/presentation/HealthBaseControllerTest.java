package com.example.oauth2.health.presentation;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.example.oauth2.auth.infrastructure.jwt.JwtProvider;
import com.example.oauth2.auth.presentation.support.AuthContext;
import com.example.oauth2.common.ControllerTest;
import com.example.oauth2.health.presentation.dto.HealthResponse;

@WebMvcTest(HealthController.class)
class HealthControllerTest extends ControllerTest {

	@MockBean
	AuthContext authContext;

	@MockBean
	JwtProvider jwtProvider;

	@Test
	void healthCheckTest() throws Exception {
		// given
		HealthResponse response = new HealthResponse("ok");

		// when & then
		String content = mockMvc.perform(get("/api/v1/health-check"))
			.andExpect(status().isOk())
			.andDo(document(
					"hello",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					resource(
						ResourceSnippetParameters.builder()
							.tag("health")
							.summary("summery 입니다")
							.description("description 임")
							.responseFields(
								fieldWithPath("state").description("상태")
							)
							.responseSchema(Schema.schema("HealthResponse"))
							.build()
					)
				)
			)
			.andReturn()
			.getResponse()
			.getContentAsString(StandardCharsets.UTF_8);
		HealthResponse actual = objectMapper.readValue(content, HealthResponse.class);

		Assertions.assertThat(actual).isEqualTo(response);
	}
}
