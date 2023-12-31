package com.alex.picpaychallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> emailAlreadyExists(EmailAlreadyExistsException ex) {
        var response = new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(response.statusCode()).body(response);
    }
    @ExceptionHandler(DocumentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> documentAlreadyExists(DocumentAlreadyExistsException ex) {
        var response = new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT.value());
        return ResponseEntity.status(response.statusCode()).body(response);
    }
    @ExceptionHandler(EmailServiceDownException.class)
    public ResponseEntity<ErrorResponse> emailServiceDown(EmailServiceDownException ex) {
        var response = new ErrorResponse(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE.value());
        return ResponseEntity.status(response.statusCode()).body(response);
    }

}
