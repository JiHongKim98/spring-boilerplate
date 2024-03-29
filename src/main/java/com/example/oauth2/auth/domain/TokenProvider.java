package com.example.oauth2.auth.domain;

public interface TokenProvider {

	String generatedAccessToken(Long memberId);

	String generatedRefreshToken(String tokenId);
}
