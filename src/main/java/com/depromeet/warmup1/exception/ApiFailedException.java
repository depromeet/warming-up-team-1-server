package com.depromeet.warmup1.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiFailedException extends RuntimeException {
	private final HttpStatus httpStatus;

	public ApiFailedException() {
		httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public ApiFailedException(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ApiFailedException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}
}