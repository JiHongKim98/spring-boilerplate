package com.example.oauth2.auth.application;

public interface TokenExtractor {

	Long extractAccessToken(String token);

	String extractRefreshToken(String token);
}
