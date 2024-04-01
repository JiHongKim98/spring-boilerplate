package com.example.demo.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.example.demo.auth.domain.Token;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

	private final String host;
	private final int port;

	public RedisConfig(
		@Value("${spring.redis.host}") String host,
		@Value("${spring.redis.port}") int port
	) {
		this.host = host;
		this.port = port;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(host, port);
	}

	@Bean
	public RedisTemplate<String, Token> redisTemplate() {
		RedisTemplate<String, Token> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}
}
