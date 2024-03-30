package com.example.demo.member.fixture;

import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.Role;
import com.example.demo.member.domain.SocialType;

public class MemberFixture {

	public static Member normalMember() {
		return Member.builder()
			.name("normal-member-name")
			.email("normal-member@email.com")
			.role(Role.MEMBER)
			.socialType(SocialType.GOOGLE)
			.imageUri("imageUri")
			.socialId("1234567890")
			.build();
	}

	public static Member adminMember() {
		return Member.builder()
			.name("admin-member-name")
			.email("admin-member@email.com")
			.role(Role.ADMIN)
			.socialType(SocialType.GOOGLE)
			.imageUri("imageUri")
			.socialId("1234567890")
			.build();
	}
}
