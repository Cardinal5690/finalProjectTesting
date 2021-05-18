package com.testing.model.exception;

public class ResultException extends RuntimeException{
    public ResultException() {
    }

    public ResultException(String message) {
        super(message);
    }
}
