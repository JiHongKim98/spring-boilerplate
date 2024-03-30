package com.example.demo.auth.infrastructure;

import static io.jsonwebtoken.SignatureAlgorithm.*;
import static io.jsonwebtoken.security.Keys.*;
import static org.assertj.core.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.auth.exception.AuthException;
import com.example.demo.auth.exception.AuthExceptionType;
import com.example.demo.auth.infrastructure.jwt.JwtExtractor;
import com.example.demo.member.domain.Role;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtExtractorTest {

	private static final String SECRET_KEY = "testtesttesttesttesttesttesttest";
	private final JwtExtractor jwtExtractor = new JwtExtractor(SECRET_KEY);

	@DisplayName("JWT 복호화 성공.")
	@Test
	void tokenExtractSuccessWithValidTokenTest() {
		// given
		String token = Jwts.builder()
			.claim("memberId", 1L)
			.claim("role", Role.MEMBER)
			.setExpiration(new Date(System.currentTimeMillis() + 100000))
			.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), HS256)
			.compact();

		// when
		Long memberId = jwtExtractor.extractAccessToken(token);

		// then
		assertThat(memberId).isEqualTo(1L);
	}

	@DisplayName("JWT 복호화 실패시 예외를 반환.")
	@Test
	void tokenExtractFailWithInvalidTokenTest() {
		// given
		String invalidToken = "invalid-access-token";

		// when & then
		assertThatThrownBy(() -> jwtExtractor.extractAccessToken(invalidToken))
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
		assertThatThrownBy(() -> jwtExtractor.extractAccessToken(expiredToken))
			.isInstanceOf(AuthException.class)
			.hasMessage(AuthExceptionType.EXPIRED_TOKEN.message());
	}
}
