package com.example.demo.auth.application;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.auth.exception.AuthException;
import com.example.demo.auth.exception.AuthExceptionType;

@Component
public class OAuthClientHandler {

	private final List<OAuthClient> oAuthClientList;

	public OAuthClientHandler(List<OAuthClient> oAuthClientList) {
		this.oAuthClientList = oAuthClientList;
	}

	public OAuthClient getOAuthClient(String socialType) {
		return oAuthClientList.stream()
			.filter(it -> it.isFitOAuthClient(socialType))
			.findFirst()
			.orElseThrow(() -> new AuthException(AuthExceptionType.INVALID_OAUTH_TYPE));
	}
}
