package com.example.oauth2.member.application.dto;

import com.example.oauth2.member.domain.Member;
import com.example.oauth2.member.domain.Role;

public record MyInfoResponse(
	Long id,
	String email,
	String name,
	String imageUri,
	Role role
) {

	public static MyInfoResponse of(Member member) {
		return new MyInfoResponse(
			member.getId(),
			member.getEmail(),
			member.getName(),
			member.getImageUri(),
			member.getRole()
		);
	}
}
