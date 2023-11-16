package com.division.exception;

public class DivisonByZeroException extends Exception {
    public DivisonByZeroException() {
    }

    public DivisonByZeroException(String message) {
        super(message);
    }
}
