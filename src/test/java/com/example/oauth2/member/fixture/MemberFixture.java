package com.example.oauth2.member.fixture;

import com.example.oauth2.member.domain.Member;
import com.example.oauth2.member.domain.Role;
import com.example.oauth2.member.domain.SocialType;

public class MemberFixture {

	public static Member createMember(String name, String email, Role role) {
		return Member.builder()
			.name(name)
			.email(email)
			.role(role)
			.socialType(SocialType.GOOGLE)
			.imageUri("imageUri")
			.socialId("1234567890")
			.build();
	}
}
