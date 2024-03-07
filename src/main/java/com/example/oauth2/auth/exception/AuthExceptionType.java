package com.example.oauth2.auth.exception;

import org.springframework.http.HttpStatus;

public enum AuthExceptionType {
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증 정보가 제공되지 않았습니다."),
	EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "이미 만료된 토큰입니다."),
	INVALID_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않는 토큰입니다."),
	INVALID_OAUTH_TYPE(HttpStatus.BAD_REQUEST, "지원하지 않는 OAUTH 형식입니다.");

	private final HttpStatus status;
	private final String message;

	AuthExceptionType(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public String message() {
		return message;
	}

	public HttpStatus status() {
		return status;
	}
}
