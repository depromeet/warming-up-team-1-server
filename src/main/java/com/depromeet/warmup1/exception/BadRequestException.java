package com.depromeet.warmup1.exception;

public class BadRequestException extends RuntimeException {

	public BadRequestException() {

	}

	public BadRequestException(String message) {
		super(message);
	}
}