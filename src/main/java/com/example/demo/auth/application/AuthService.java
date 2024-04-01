package com.example.demo.auth.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.auth.application.dto.OAuthInfo;
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
	private final TokenProvider tokenProvider;
	private final TokenRepository tokenRepository;

	@Transactional
	public TokenResponse loginOrRegister(OAuthInfo oAuthInfo) {
		Member member = memberRepository.findBySocialId(oAuthInfo.socialId())
			.orElseGet(() -> memberRepository.save(oAuthInfo.toMember()));
		return createToken(member.getId());
	}

	private TokenResponse createToken(Long memberId) {
		Token token = new Token(memberId);
		tokenRepository.save(token);
		return generatedTokenPair(token);
	}

	private TokenResponse generatedTokenPair(Token token) {
		String accessToken = tokenProvider.generatedAccessToken(token.getMemberId());
		String refreshToken = tokenProvider.generatedRefreshToken(token.getTokenId());
		return TokenResponse.of(accessToken, refreshToken);
	}

	public TokenResponse reissueToken(String tokenId) {
		Token token = tokenRepository.findByTokenId(tokenId)
			.orElseThrow(() -> new AuthException(AuthExceptionType.INVALID_TOKEN));
		tokenRepository.deleteByTokenId(token.getTokenId());
		return createToken(token.getMemberId());
	}
}
