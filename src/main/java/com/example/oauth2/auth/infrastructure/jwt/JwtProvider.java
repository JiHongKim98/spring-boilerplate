package com.example.oauth2.auth.infrastructure.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.oauth2.auth.domain.TokenProvider;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider implements TokenProvider {

	private static final String MEMBER_ID = "memberId";

	private final SecretKey key;
	private final Long exp;

	public JwtProvider(
		@Value("${jwt.secret-key}") String secretKey,
		@Value("${jwt.exp}") Long exp
	) {
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		this.exp = exp;
	}

	@Override
	public String generated(Long memberId) {
		long now = System.currentTimeMillis();
		return Jwts.builder()
			.claim(MEMBER_ID, memberId)
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + exp))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}
}
