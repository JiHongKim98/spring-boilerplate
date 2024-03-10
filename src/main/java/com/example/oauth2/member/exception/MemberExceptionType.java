package com.example.oauth2.member.exception;

import org.springframework.http.HttpStatus;

import com.example.oauth2.common.exception.ExceptionType;

public enum MemberExceptionType implements ExceptionType {
	NOT_EXIST_MEMBER(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
	;

	private final HttpStatus status;
	private final String message;

	MemberExceptionType(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	@Override
	public String message() {
		return message;
	}

	@Override
	public String code() {
		return this.name();
	}

	@Override
	public HttpStatus status() {
		return status;
	}
}
