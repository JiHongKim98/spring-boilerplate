package com.example.oauth2.common;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.oauth2.auth.application.AuthService;
import com.example.oauth2.auth.infrastructure.jwt.JwtProvider;
import com.example.oauth2.auth.presentation.support.AuthContext;
import com.fasterxml.jackson.databind.ObjectMapper;

// @MockBean(JpaMetamodelMappingContext.class)  // @EnableJpaAuditing 사용시
@ExtendWith({RestDocumentationExtension.class})
public abstract class ControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@MockBean
	protected JwtProvider jwtProvider;

	@MockBean
	protected AuthContext authContext;

	@MockBean
	protected AuthService authService;

	@BeforeEach
	void setUp(
		WebApplicationContext context,
		RestDocumentationContextProvider provider
	) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
			.apply(documentationConfiguration(provider))
			.build();
	}
}
