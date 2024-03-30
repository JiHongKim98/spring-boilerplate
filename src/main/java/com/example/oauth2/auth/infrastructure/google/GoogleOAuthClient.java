package com.example.oauth2.auth.infrastructure.google;

import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.example.oauth2.auth.application.OAuthClient;
import com.example.oauth2.auth.application.dto.OAuthInfo;
import com.example.oauth2.auth.infrastructure.google.dto.GoogleToken;
import com.example.oauth2.auth.infrastructure.google.dto.GoogleUserInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleOAuthClient implements OAuthClient {

	private static final String PROVIDER = "google";

	private final GoogleProperties properties;
	private final GoogleApiClient client;

	@Override
	public boolean isFitOAuthClient(String socialType) {
		return socialType.equalsIgnoreCase(PROVIDER);  // 대소문자 미구분
	}

	@Override
	public OAuthInfo getOAuthInfo(String code) {
		GoogleToken googleToken = client.fetchToken(params(code));
		GoogleUserInfo googleUserInfo = client.fetchUserInfo("Bearer " + googleToken.accessToken());
		return googleUserInfo.toOAuthInfo();
	}

	private MultiValueMap<String, String> params(String code) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("code", code);
		params.add("client_id", properties.clientId());
		params.add("client_secret", properties.clientSecret());
		params.add("redirect_uri", properties.redirectUri());
		params.add("grant_type", properties.grantType());
		return params;
	}
}
