package com.example.demo.auth.application.dto;

import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.Role;
import com.example.demo.member.domain.SocialType;

import lombok.Builder;

@Builder
public record OAuthInfo(
	SocialType socialType,
	String socialId,
	String email,
	String name,
	String imageUri
) {

	public Member toMember() {
		return Member.builder()
			.socialType(socialType)
			.socialId(socialId)
			.email(email)
			.name(name)
			.imageUri(imageUri)
			.role(Role.MEMBER)
			.build();
	}
}
