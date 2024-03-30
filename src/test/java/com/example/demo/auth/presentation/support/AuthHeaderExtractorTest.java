package com.example.demo.auth.presentation.support;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;

class AuthHeaderExtractorTest {

	private final MockHttpServletRequest request = new MockHttpServletRequest();

	@DisplayName("요청의 인증 헤더에 토큰이 있다면 토큰을 반환.")
	@Test
	void getTokenInHeaderWithPresentTest() {
		// given
		request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer token");

		// when
		Optional<String> actual = AuthHeaderExtractor.extract(request);

		// then
		assertThat(actual).isPresent();
	}

	@DisplayName("요청의 인증 헤더가 있지만 Bearer 타입이 아니면 빈 값을 반환.")
	@Test
	void getTokenInHeaderWithOtherTypeTest() {
		// given
		request.addHeader(HttpHeaders.AUTHORIZATION, "Basic token");

		// when
		Optional<String> actual = AuthHeaderExtractor.extract(request);

		// then
		assertThat(actual).isEmpty();
	}

	@DisplayName("요청에 인증 헤더가 없을 경우 빈 값을 반환.")
	@Test
	void getTokenInHeaderWithEmptyHeaderTest() {
		// given (none)

		// when
		Optional<String> actual = AuthHeaderExtractor.extract(request);

		// then
		assertThat(actual).isEmpty();
	}
}
