package com.example.demo.auth.exception;

import com.example.demo.common.exception.BaseException;
import com.example.demo.common.exception.ExceptionType;

public class AuthException extends BaseException {

	public AuthException(ExceptionType exceptionType) {
		super(exceptionType);
	}
}
