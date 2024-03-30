package com.example.demo.auth.application;

import com.example.demo.auth.application.dto.OAuthInfo;

public interface OAuthClient {

	boolean isFitOAuthClient(String providerName);

	OAuthInfo getOAuthInfo(String code);
}
