package com.example.oauth2.auth.application;

import com.example.oauth2.auth.application.dto.OAuthInfo;

public interface OAuthClient {

	boolean isFitOAuthClient(String providerName);

	OAuthInfo getOAuthInfo(String code);
}
