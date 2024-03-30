package com.example.demo.common.exception;

public class BaseException extends RuntimeException {

	private final ExceptionType exceptionType;

	public BaseException(ExceptionType exceptionType) {
		super(exceptionType.message());
		this.exceptionType = exceptionType;
	}

	public ExceptionType getType() {
		return exceptionType;
	}
}
