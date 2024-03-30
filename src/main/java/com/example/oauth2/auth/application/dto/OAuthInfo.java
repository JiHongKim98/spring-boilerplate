package com.example.oauth2.auth.application.dto;

import com.example.oauth2.member.domain.Member;
import com.example.oauth2.member.domain.Role;
import com.example.oauth2.member.domain.SocialType;

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
