package com.example.oauth2.auth.application;

public interface TokenProvider {

	String generatedAccessToken(Long memberId);

	String generatedRefreshToken(String tokenId);
}
