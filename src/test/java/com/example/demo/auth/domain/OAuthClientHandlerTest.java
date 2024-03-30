package com.example.demo.auth.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.auth.application.OAuthClient;
import com.example.demo.auth.application.OAuthClientHandler;
import com.example.demo.auth.exception.AuthException;
import com.example.demo.auth.exception.AuthExceptionType;
import com.example.demo.auth.infrastructure.FakeOAuthClient;

class OAuthClientHandlerTest {

	@DisplayName("해당 OAuthClient 가 등록된 경우 OAuthClient 반환.")
	@Test
	void getOAuthClientTest() {
		// given
		OAuthClientHandler oAuthClientHandler = new OAuthClientHandler(List.of(new FakeOAuthClient()));

		// when
		OAuthClient oAuthClient = oAuthClientHandler.getOAuthClient("fake");

		// then
		assertThat(oAuthClient).isInstanceOf(FakeOAuthClient.class);
	}

	@DisplayName("해당 OAuthClient 가 등록되지 않은 경우 예외 반환.")
	@Test
	void getOAuthClientWithExceptionTest() {
		// given
		OAuthClientHandler oAuthClientHandler = new OAuthClientHandler(List.of());

		// when & then
		assertThatThrownBy(() -> oAuthClientHandler.getOAuthClient("fake"))
			.isInstanceOf(AuthException.class)
			.hasMessage(AuthExceptionType.INVALID_OAUTH_TYPE.message());
	}
}
