package com.example.oauth2.auth.infrastructure;

import com.example.oauth2.auth.domain.OAuthClient;
import com.example.oauth2.auth.domain.OAuthInfo;
import com.example.oauth2.member.domain.Member;
import com.example.oauth2.member.fixture.MemberFixture;

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
