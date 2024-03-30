package com.example.demo.auth.application;

public interface TokenExtractor {

	Long extractAccessToken(String token);

	String extractRefreshToken(String token);
}
