package com.example.oauth2.auth.infrastructure.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.oauth2.auth.domain.AuthPayload;
import com.example.oauth2.auth.domain.TokenProvider;
import com.example.oauth2.auth.exception.AuthException;
import com.example.oauth2.auth.exception.AuthExceptionType;
import com.example.oauth2.member.domain.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider implements TokenProvider {

	private static final String MEMBER_ID = "memberId";
	private static final String ROLE = "role";

	private final SecretKey SECRET_KEY;
	private final Long EXPIRED_TIME;
	private final JwtParser jwtParser;

	public JwtProvider(
		@Value("${jwt.secret-key}") String key,
		@Value("${jwt.exp}") Long expiredTime
	) {
		this.SECRET_KEY = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
		this.EXPIRED_TIME = expiredTime;
		this.jwtParser = Jwts.parserBuilder()
			.setSigningKey(SECRET_KEY)
			.build();
	}

	@Override
	public String generated(AuthPayload authPayload) {
		long now = System.currentTimeMillis();
		return Jwts.builder()
			.claim(MEMBER_ID, authPayload.memberId())
			.claim(ROLE, authPayload.role())
			.setIssuedAt(new Date(now))
			.setExpiration(new Date(now + EXPIRED_TIME))
			.signWith(SECRET_KEY, SignatureAlgorithm.HS256)
			.compact();
	}

	@Override
	public AuthPayload extract(String token) {
		Claims claims = parseClaim(token);
		Long memberId = claims.get(MEMBER_ID, Long.class);
		String role = claims.get(ROLE, String.class);
		return new AuthPayload(memberId, Role.of(role));
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
