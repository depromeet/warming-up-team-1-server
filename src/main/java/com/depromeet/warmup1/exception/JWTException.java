package com.depromeet.warmup1.exception;

import org.springframework.http.HttpStatus;

public class JWTException extends RuntimeException {
    private final HttpStatus httpStatus;

    public JWTException() {
        httpStatus = HttpStatus.UNAUTHORIZED;
    }

    public JWTException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public JWTException(String message) {
        super(message);
        httpStatus = HttpStatus.UNAUTHORIZED;
    }

    public JWTException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
