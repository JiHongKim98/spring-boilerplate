package com.example.demo.auth.infrastructure;

import com.example.demo.auth.application.OAuthClient;
import com.example.demo.auth.application.dto.OAuthInfo;
import com.example.demo.member.domain.Member;
import com.example.demo.member.fixture.MemberFixture;

public class FakeOAuthClient implements OAuthClient {

	@Override
	public boolean isFitOAuthClient(String providerName) {
		return true;
	}

	@Override
	public OAuthInfo getOAuthInfo(String code) {
		Member member = MemberFixture.normalMember();
		return new OAuthInfo(
			member.getSocialType(),
			member.getSocialId(),
			member.getEmail(),
			member.getName(),
			member.getImageUri()
		);
	}
}
