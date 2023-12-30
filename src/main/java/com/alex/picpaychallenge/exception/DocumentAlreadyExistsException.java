package com.alex.picpaychallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DocumentAlreadyExistsException extends RuntimeException{
    public DocumentAlreadyExistsException(String message) {
        super(message);
    }

    public DocumentAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}