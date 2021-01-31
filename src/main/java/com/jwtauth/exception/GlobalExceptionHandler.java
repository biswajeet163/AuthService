package com.jwtauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidExpiredTokenException.class)
	public ResponseEntity<String> invalidExpiredTokenException(InvalidExpiredTokenException ex, WebRequest request) {

		return new ResponseEntity<>(ex.toString(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidUserCredentialsException.class)
	public ResponseEntity<String> dateFormatInvalidException(InvalidUserCredentialsException ex, WebRequest request) {

		return new ResponseEntity<>(ex.toString(), HttpStatus.FORBIDDEN);
	}

}