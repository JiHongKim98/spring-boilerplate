package com.example.demo.auth.infrastructure.google.dto;

import com.example.demo.auth.application.dto.OAuthInfo;
import com.example.demo.member.domain.SocialType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleUserInfo(
	@JsonProperty("sub") String socialId,
	@JsonProperty("email") String email,
	@JsonProperty("name") String name,
	@JsonProperty("picture") String imageUri
) {

	public OAuthInfo toOAuthInfo() {
		return OAuthInfo.builder()
			.socialType(SocialType.GOOGLE)
			.socialId(socialId)
			.email(email)
			.name(name)
			.imageUri(imageUri)
			.build();
	}
}
