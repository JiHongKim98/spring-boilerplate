package com.example.oauth2.auth.infrastructure;

import static io.jsonwebtoken.SignatureAlgorithm.*;
import static io.jsonwebtoken.security.Keys.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.oauth2.auth.domain.AuthPayload;
import com.example.oauth2.auth.exception.AuthException;
import com.example.oauth2.auth.exception.AuthExceptionType;
import com.example.oauth2.auth.infrastructure.jwt.JwtProvider;
import com.example.oauth2.member.domain.Role;

import io.jsonwebtoken.Jwts;

class JwtProviderTest {

	private static final String SECRET_KEY = "testtesttesttesttesttesttesttest";
	private final JwtProvider jwtProvider = new JwtProvider(SECRET_KEY, 10000L);

	@DisplayName("accessToken 을 생성.")
	@Test
	void generateTokenTest() {
		// given
		AuthPayload authPayload = new AuthPayload(1L, Role.MEMBER);

		// when
		String accessToken = jwtProvider.generated(authPayload);

		// then
		assertSoftly(softly -> {
			softly.assertThat(accessToken).isNotNull();
			softly.assertThat(jwtProvider.extract(accessToken)).isEqualTo(authPayload);
		});
	}

	@DisplayName("JWT 복호화 실패시 예외를 반환.")
	@Test
	void tokenExtractFailWithInvalidTokenTest() {
		// given
		String invalidToken = "invalid-access-token";

		// when & then
		assertThatThrownBy(() -> jwtProvider.extract(invalidToken))
			.isInstanceOf(AuthException.class)
			.hasMessage(AuthExceptionType.INVALID_TOKEN.message());
	}

	@Test
	@DisplayName("만료된 토큰일 경우 예외를 반환.")
	void tokenExtractFailWithExpiredTokenTest() {
		// given
		Date expiredDate = new Date(System.currentTimeMillis() - 1);
		String expiredToken = Jwts.builder()
			.signWith(hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), HS256)
			.setExpiration(expiredDate)
			.claim("memberId", 1L)
			.claim("role", Role.MEMBER)
			.compact();

		// when & then
		assertThatThrownBy(() -> jwtProvider.extract(expiredToken))
			.isInstanceOf(AuthException.class)
			.hasMessage(AuthExceptionType.EXPIRED_TOKEN.message());
	}
}
