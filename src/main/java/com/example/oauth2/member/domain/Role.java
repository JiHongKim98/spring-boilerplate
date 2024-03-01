package com.example.oauth2.member.domain;

public enum Role {
	MEMBER,
	ADMIN,
	;

	public static Role of(String role) {
		return valueOf(role);
	}
}
