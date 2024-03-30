package com.example.demo.auth.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.auth.application.dto.TokenResponse;
import com.example.demo.auth.infrastructure.FakeOAuthClient;
import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.respository.MemberRepository;
import com.example.demo.member.fixture.MemberFixture;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

	AuthService authService;

	OAuthClientHandler oAuthClientHandler;

	@Mock
	TokenProvider tokenProvider;

	@Mock
	MemberRepository memberRepository;

	@BeforeEach
	void setUp() {
		oAuthClientHandler = new OAuthClientHandler(List.of(new FakeOAuthClient()));
		authService = new AuthService(memberRepository, oAuthClientHandler, tokenProvider);
	}

	@DisplayName("소셜 로그인 후 인증 토큰 반환.")
	@Test
	void loginServiceTest() {
		// given
		Member member = MemberFixture.normalMember();
		given(tokenProvider.generated(anyLong()))
			.willReturn("accessToken");
		given(memberRepository.findBySocialId(anyString()))
			.willReturn(Optional.of(member));

		TokenResponse tokenResponse = TokenResponse.of("accessToken");

		// when
		TokenResponse actual = authService.loginOrRegister("google", "code");

		// then
		assertThat(actual).isEqualTo(tokenResponse);
	}
}
