package com.example.oauth2.auth.domain;

import com.example.oauth2.member.domain.Role;

public record AuthPayload(
	Long memberId,
	Role role
) {
}
