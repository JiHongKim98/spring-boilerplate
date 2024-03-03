package com.example.oauth2.auth.domain;

import java.util.List;

import org.springframework.stereotype.Component;

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
			.orElseThrow(() -> new RuntimeException("지원하지 않는 OAuthClient 입니다."));  // TODO: 예외 처리 보강
	}
}
