package com.example.oauth2.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.oauth2.auth.application.dto.TokenResponse;
import com.example.oauth2.auth.domain.AuthPayload;
import com.example.oauth2.auth.domain.OAuthClient;
import com.example.oauth2.auth.domain.OAuthClientHandler;
import com.example.oauth2.auth.domain.OAuthInfo;
import com.example.oauth2.auth.domain.TokenProvider;
import com.example.oauth2.member.domain.Member;
import com.example.oauth2.member.domain.respository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

	private final MemberRepository memberRepository;
	private final OAuthClientHandler oAuthClientHandler;
	private final TokenProvider tokenProvider;

	@Transactional
	public TokenResponse loginOrCreateMember(String socialType, String code) {
		OAuthClient oAuthClient = oAuthClientHandler.getOAuthClient(socialType);
		OAuthInfo oAuthInfo = oAuthClient.getOAuthInfo(code);

		Member member = memberRepository.findBySocialId(oAuthInfo.socialId())
			.orElseGet(() -> memberRepository.save(oAuthInfo.toMember()));

		String accessToken = tokenProvider.generated(new AuthPayload(member.getId(), member.getRole()));
		return new TokenResponse(accessToken);
	}
}
