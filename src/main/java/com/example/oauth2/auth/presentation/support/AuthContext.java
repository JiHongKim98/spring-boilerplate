package com.example.oauth2.auth.presentation.support;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuthContext {

	private Long memberId;

	public Long getMemberId() {
		if (this.memberId == null) {
			throw new RuntimeException("401 오류 - UN_AUTHORIZATION");  // TODO: 예외 처리 보강
		}
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
}
