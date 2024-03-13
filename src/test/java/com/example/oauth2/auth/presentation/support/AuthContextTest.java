package com.example.oauth2.auth.presentation.support;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.oauth2.auth.domain.AuthPayload;
import com.example.oauth2.auth.exception.AuthException;
import com.example.oauth2.auth.exception.AuthExceptionType;
import com.example.oauth2.member.domain.Role;

class AuthContextTest {

	@DisplayName("인증 정보를 반환한다.")
	@Test
	void getAuthContextTest() {
		// given
		AuthPayload authPayload = new AuthPayload(1L, Role.MEMBER);
		AuthContext authContext = new AuthContext();

		// when
		authContext.setContextFromPayload(authPayload);

		// then
		assertThat(authContext.getMemberId()).isEqualTo(authPayload.memberId());
	}

	@DisplayName("인증 정보가 없을때 예외를 반환.")
	@Test
	void getAuthContextTestWithoutAuthenticationTest() {
		// given
		AuthContext unAuthContext = new AuthContext();

		// when & then
		assertThatThrownBy(unAuthContext::getMemberId)
			.isInstanceOf(AuthException.class)
			.hasMessage(AuthExceptionType.UNAUTHORIZED.message());
	}
}
