package com.example.oauth2.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ExceptionResponse> handleException(BaseException ex) {
		log.warn(ex.getMessage(), ex);

		ExceptionType type = ex.getType();
		return ResponseEntity.status(type.status())
			.body(new ExceptionResponse(type.code(), ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
		log.error(ex.getMessage(), ex);

		return ResponseEntity.internalServerError()
			.body(new ExceptionResponse("INTERNAL_SERVER_ERROR", ex.getMessage()));
	}
}
