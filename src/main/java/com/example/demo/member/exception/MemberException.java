package com.example.demo.member.exception;

import com.example.demo.common.exception.BaseException;
import com.example.demo.common.exception.ExceptionType;

public class MemberException extends BaseException {

	public MemberException(ExceptionType exceptionType) {
		super(exceptionType);
	}
}
