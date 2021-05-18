package com.testing.model.exception;

public class WrongDataException extends Exception{
    public WrongDataException() {
    }

    public WrongDataException(String message) {
        super(message);
    }
}
