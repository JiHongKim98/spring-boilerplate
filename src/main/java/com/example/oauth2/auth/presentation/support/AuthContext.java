package com.example.oauth2.auth.presentation.support;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.example.oauth2.auth.exception.AuthException;
import com.example.oauth2.auth.exception.AuthExceptionType;

@Component
@RequestScope
public class AuthContext {

	private Long memberId;

	public Long getMemberId() {
		if (this.memberId == null) {
			throw new AuthException(AuthExceptionType.UNAUTHORIZED);
		}
		return memberId;
	}

	// public void setMemberId(Long memberId) {
	// 	this.memberId = memberId;
	// }

	public void setContext(Long memberId) {
		this.memberId = memberId;
	}
}
