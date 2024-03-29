package com.example.oauth2.auth.application.dto;

public record TokenResponse(
	String accessToken,
	String refreshToken
) {

	public static TokenResponse of(String accessToken, String refreshToken) {
		return new TokenResponse(accessToken, refreshToken);
	}
}
