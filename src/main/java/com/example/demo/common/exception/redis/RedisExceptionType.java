package com.example.demo.common.exception.redis;

import org.springframework.http.HttpStatus;

import com.example.demo.common.exception.ExceptionType;

public enum RedisExceptionType implements ExceptionType {
	SERIALIZE_ERROR(HttpStatus.BAD_REQUEST, "직렬화 과정중 오류가 발생했습니다."),
	DESERIALIZE_ERROR(HttpStatus.BAD_REQUEST, "역직렬화 과정중 오류가 발생했습니다"),
	;

	private final HttpStatus status;
	private final String message;

	RedisExceptionType(HttpStatus status, String message) {
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
