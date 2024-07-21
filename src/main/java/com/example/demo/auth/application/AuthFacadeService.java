package com.example.demo.auth.application;

import org.springframework.stereotype.Service;

import com.example.demo.auth.application.dto.OAuthInfo;
import com.example.demo.auth.application.dto.TokenResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthFacadeService {

	private final AuthService authService;
	private final TokenExtractor tokenExtractor;
	private final OAuthClientHandler oAuthClientHandler;

	public TokenResponse loginOrRegister(String socialType, String code) {
		OAuthClient oAuthClient = oAuthClientHandler.getOAuthClient(socialType);
		OAuthInfo oAuthInfo = oAuthClient.getOAuthInfo(code);
		return authService.loginOrRegister(oAuthInfo);
	}

	public TokenResponse reissueToken(String refreshToken) {
		String tokenId = tokenExtractor.extractRefreshToken(refreshToken);
		return authService.reissueToken(tokenId);
	}

	public void logout(Long memberId, String refreshToken) {
		String tokenId = tokenExtractor.extractRefreshToken(refreshToken);
		authService.logout(memberId, tokenId);
	}
}
