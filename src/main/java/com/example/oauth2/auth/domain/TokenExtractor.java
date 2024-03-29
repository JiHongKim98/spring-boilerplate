package com.example.oauth2.auth.domain;

public interface TokenExtractor {

	Long extractAccessToken(String token);

	String extractRefreshToken(String token);
}
