package com.example.demo.auth.infrastructure.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.demo.auth.application.TokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider implements TokenProvider {

	private static final String MEMBER_ID = "member_id";
	private static final String TOKEN_ID = "token_id";
	private static final String ACCESS_TOKEN = "access_token";
	private static final String REFRESH_TOKEN = "refresh_token";

	private final SecretKey key;
	private final Long accessTokenExpired;
	private final Long refreshTokenExpired;

	public JwtProvider(
		@Value("${jwt.secret-key}") String secretKey,
		@Value("${jwt.access-exp}") Long accessTokenExpired,
		@Value("${jwt.refresh-exp}") Long refreshTokenExpired
	) {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		this.accessTokenExpired = accessTokenExpired;
		this.refreshTokenExpired = refreshTokenExpired;
	}

	@Override
	public String generatedAccessToken(Long memberId) {
		Claims claims = generatedClaims(MEMBER_ID, memberId);
		return generatedToken(claims, ACCESS_TOKEN, accessTokenExpired);
	}

	@Override
	public String generatedRefreshToken(String tokenId) {
		Claims claims = generatedClaims(TOKEN_ID, tokenId);
		return generatedToken(claims, REFRESH_TOKEN, refreshTokenExpired);
	}

	private Claims generatedClaims(String key, Object value) {
		Claims claims = Jwts.claims();
		claims.put(key, value);
		return claims;
	}

	private String generatedToken(Claims claims, String subject, Long exp) {
		long now = System.currentTimeMillis();
		return Jwts.builder()
			.setClaims(claims)
			.setSubject(subject)
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + exp))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}
}
