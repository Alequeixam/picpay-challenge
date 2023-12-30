package com.alex.picpaychallenge.exception;

public record ErrorResponse(String message,
                            int statusCode) {
}
