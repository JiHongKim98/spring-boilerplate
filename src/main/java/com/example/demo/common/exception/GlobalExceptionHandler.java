package com.example.demo.common.exception;

import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex,
		HttpHeaders headers,
		HttpStatusCode status,
		WebRequest request
	) {
		log.warn(ex.getMessage(), ex);

		String message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
		return ResponseEntity.status(status)
			.body(new ExceptionResponse("ARGUMENT_NOT_VALID", message));
	}
}
