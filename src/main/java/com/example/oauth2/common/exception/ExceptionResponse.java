package com.example.oauth2.common.exception;

public record ExceptionResponse(
	String code,
	String message
) {
}
