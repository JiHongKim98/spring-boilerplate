package com.example.oauth2.auth.exception;

import com.example.oauth2.common.exception.BaseException;
import com.example.oauth2.common.exception.ExceptionType;

public class AuthException extends BaseException {

	public AuthException(ExceptionType exceptionType) {
		super(exceptionType);
	}
}
