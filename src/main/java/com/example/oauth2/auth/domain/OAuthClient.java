package com.example.oauth2.auth.domain;

public interface OAuthClient {

	boolean isFitOAuthClient(String providerName);

	OAuthInfo getOAuthInfo(String code);
}
