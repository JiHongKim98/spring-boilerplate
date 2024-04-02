package com.example.demo.common.exception.redis;

import com.example.demo.common.exception.BaseException;
import com.example.demo.common.exception.ExceptionType;

public class RedisException extends BaseException {

	public RedisException(ExceptionType exceptionType) {
		super(exceptionType);
	}
}
