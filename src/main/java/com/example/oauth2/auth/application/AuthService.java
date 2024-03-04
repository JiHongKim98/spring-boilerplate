package com.example.oauth2.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.oauth2.auth.domain.OAuthClient;
import com.example.oauth2.auth.domain.OAuthClientHandler;
import com.example.oauth2.auth.domain.OAuthInfo;
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

	@Transactional
	public void loginOrCreateMember(String socialType, String code) {
		OAuthClient oAuthClient = oAuthClientHandler.getOAuthClient(socialType);
		OAuthInfo oAuthInfo = oAuthClient.getOAuthInfo(code);

		Member member = memberRepository.findBySocialId(oAuthInfo.socialId())
			.orElseGet(() -> memberRepository.save(oAuthInfo.toMember()));

		log.info("member => {}", member);

		// TODO: JWT 발급 로직 추가
	}
}
