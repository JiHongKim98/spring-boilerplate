package com.example.demo.auth.infrastructure.redis;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.auth.domain.Token;
import com.example.demo.auth.domain.respository.TokenRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisTokenRepository implements TokenRepository {

	private static final Long TTL = 10_080L;

	private final ObjectMapper objectMapper;
	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public void save(Token token) {
		redisTemplate.opsForValue().set(token.getTokenId(), serializeToken(token));
		redisTemplate.expire(token.getTokenId(), TTL, TimeUnit.MINUTES);
	}

	@Override
	public void deleteByTokenId(String tokenId) {
		redisTemplate.delete(tokenId);
	}

	@Override
	public Optional<Token> findByTokenId(String tokenId) {
		return Optional.ofNullable(redisTemplate.opsForValue().get(tokenId))
			.map(this::deserializeToken);
	}

	private String serializeToken(Token token) {
		try {
			return objectMapper.writeValueAsString(token);
		} catch (JsonProcessingException ex) {
			throw new RuntimeException(ex);  // TODO: 예외 처리 보강
		}
	}

	private Token deserializeToken(String tokenJson) {
		try {
			return objectMapper.readValue(tokenJson, Token.class);
		} catch (JsonProcessingException ex) {
			throw new RuntimeException(ex);  // TODO: 예외 처리 보강
		}
	}
}
