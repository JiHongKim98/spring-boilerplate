package com.example.oauth2.auth.infrastructure.jwt;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.oauth2.auth.domain.TokenExtractor;
import com.example.oauth2.auth.exception.AuthException;
import com.example.oauth2.auth.exception.AuthExceptionType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtExtractor implements TokenExtractor {

	private static final String MEMBER_ID = "memberId";

	private final JwtParser jwtParser;

	public JwtExtractor(@Value("${jwt.secret-key}") String secretKey) {
		SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
		this.jwtParser = Jwts.parserBuilder()
			.setSigningKey(key)
			.build();
	}

	@Override
	public Long extract(String token) {
		Claims claims = parseClaim(token);
		return claims.get(MEMBER_ID, Long.class);
	}

	private Claims parseClaim(String token) {
		try {
			return jwtParser.parseClaimsJws(token)
				.getBody();
		} catch (ExpiredJwtException ex) {
			throw new AuthException(AuthExceptionType.EXPIRED_TOKEN);
		} catch (Exception ex) {
			throw new AuthException(AuthExceptionType.INVALID_TOKEN);
		}
	}
}
