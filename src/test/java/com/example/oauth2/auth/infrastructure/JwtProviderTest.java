package com.example.oauth2.auth.infrastructure;

import static org.assertj.core.api.Assertions.*;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.oauth2.auth.domain.AuthPayload;
import com.example.oauth2.auth.infrastructure.jwt.JwtProvider;
import com.example.oauth2.member.domain.Role;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

class JwtProviderTest {

	private static final String SECRET_KEY = "testtesttesttesttesttesttesttest";
	private final JwtProvider jwtProvider = new JwtProvider(SECRET_KEY, 10000L);

	@DisplayName("accessToken 을 생성.")
	@Test
	void generateTokenTest() {
		// given
		AuthPayload authPayload = new AuthPayload(1L, Role.MEMBER);
		JwtParser parser = Jwts.parserBuilder()
			.setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
			.build();

		// when
		String accessToken = jwtProvider.generated(authPayload);

		// then
		assertThat(parser.isSigned(accessToken)).isTrue();
	}
}
