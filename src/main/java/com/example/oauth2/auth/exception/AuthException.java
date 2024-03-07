package com.example.oauth2.auth.exception;

public class AuthException extends RuntimeException {

	public AuthException(AuthExceptionType exceptionType) {
		super(exceptionType.message());
	}
}
