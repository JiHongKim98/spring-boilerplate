package com.example.oauth2.auth.infrastructure.google.dto;

import com.example.oauth2.auth.application.dto.OAuthInfo;
import com.example.oauth2.member.domain.SocialType;
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
