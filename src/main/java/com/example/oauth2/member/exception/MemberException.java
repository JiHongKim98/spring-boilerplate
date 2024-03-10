package com.example.oauth2.member.exception;

import com.example.oauth2.common.exception.BaseException;
import com.example.oauth2.common.exception.ExceptionType;

public class MemberException extends BaseException {

	public MemberException(ExceptionType exceptionType) {
		super(exceptionType);
	}
}
