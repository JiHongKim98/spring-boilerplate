package com.example.demo.member.application.dto;

import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.Role;

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
