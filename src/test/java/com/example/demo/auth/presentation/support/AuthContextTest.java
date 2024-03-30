package com.example.demo.auth.presentation.support;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.auth.exception.AuthException;
import com.example.demo.auth.exception.AuthExceptionType;

class AuthContextTest {

	@DisplayName("인증 정보를 반환한다.")
	@Test
	void getAuthContextTest() {
		// given
		Long memberId = 1L;
		AuthContext authContext = new AuthContext();

		// when
		authContext.setContext(memberId);

		// then
		assertThat(authContext.getMemberId()).isEqualTo(memberId);
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
