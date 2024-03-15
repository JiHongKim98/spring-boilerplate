package com.example.oauth2.member.presentation;

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
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.ResultActions;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.example.oauth2.common.ControllerTest;
import com.example.oauth2.member.application.dto.MyInfoResponse;
import com.example.oauth2.member.domain.Member;
import com.example.oauth2.member.fixture.MemberFixture;

@WebMvcTest(MemberController.class)
class MemberControllerTest extends ControllerTest {

	@Test
	@DisplayName("인증 토큰 기반으로 사용자 본인의 정보를 반환.")
	void getOwnInfoTest() throws Exception {
		// given
		Member member = MemberFixture.normalMember();
		MyInfoResponse myInfoResponse = MyInfoResponse.of(member);
		when(memberService.getOwnInfo(anyLong()))
			.thenReturn(myInfoResponse);

		// when
		ResultActions action = mockMvc.perform(get("/api/v1/members/me")
			.header(HttpHeaders.AUTHORIZATION, "Bearer Value-Of-Token")
		);

		// then
		action
			.andExpect(status().isOk())
			.andExpect(content().json(objectMapper.writeValueAsString(myInfoResponse)))

			// Docs
			.andDo(document(
				"simple-identifier",
				resource(ResourceSnippetParameters.builder()
					.tag("Member")
					.summary("Get My Info")
					.description("인증 토큰을 기반으로 사용자 본인의 정보를 가져온다.")
					.responseFields(
						fieldWithPath("id").description("사용자 식별자"),
						fieldWithPath("email").description("사용자 이메일"),
						fieldWithPath("name").description("사용자 이름"),
						fieldWithPath("image_uri").description("사용자 이미지 리소스 주소"),
						fieldWithPath("role").description("사용자 권한")
					).responseSchema(Schema.schema("MyInfoResponse"))
					.build())
			));
	}
}
