package com.example.demo.auth.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.auth.application.dto.OAuthInfo;
import com.example.demo.auth.application.dto.ReissueResponse;
import com.example.demo.auth.application.dto.TokenResponse;
import com.example.demo.auth.domain.Token;
import com.example.demo.auth.domain.respository.TokenRepository;
import com.example.demo.auth.exception.AuthException;
import com.example.demo.auth.exception.AuthExceptionType;
import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.respository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

	private final MemberRepository memberRepository;
	private final OAuthClientHandler oAuthClientHandler;
	private final TokenProvider tokenProvider;
	private final TokenExtractor tokenExtractor;
	private final TokenRepository tokenRepository;

	@Transactional
	public TokenResponse loginOrRegister(String socialType, String code) {
		OAuthClient oAuthClient = oAuthClientHandler.getOAuthClient(socialType);
		OAuthInfo oAuthInfo = oAuthClient.getOAuthInfo(code);

		Member member = memberRepository.findBySocialId(oAuthInfo.socialId())
			.orElseGet(() -> memberRepository.save(oAuthInfo.toMember()));

		String tokenId = UUID.randomUUID().toString();
		Token token = Token.builder()
			.memberId(member.getId())
			.tokenId(tokenId)
			.build();

		tokenRepository.save(token);

		String accessToken = tokenProvider.generatedAccessToken(member.getId());
		String refreshToken = tokenProvider.generatedRefreshToken(tokenId);

		return TokenResponse.of(accessToken, refreshToken);
	}

	public ReissueResponse reissueToken(String refreshToken) {
		String tokenId = tokenExtractor.extractRefreshToken(refreshToken);

		Token token = tokenRepository.findByTokenId(tokenId)
			.orElseThrow(() -> new AuthException(AuthExceptionType.INVALID_TOKEN));

		String accessToken = tokenProvider.generatedAccessToken(token.getMemberId());

		return ReissueResponse.of(accessToken);
	}
}
