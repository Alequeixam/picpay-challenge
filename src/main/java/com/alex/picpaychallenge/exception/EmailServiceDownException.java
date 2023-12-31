package com.alex.picpaychallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
public class EmailServiceDownException extends RuntimeException{
    public EmailServiceDownException(String message) {
        super(message);
    }

    public EmailServiceDownException(String message, Throwable cause) {
        super(message, cause);
    }
}