package com.cxa.test.exception;

public class GridPathCalculatorInvalidArgumentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GridPathCalculatorInvalidArgumentException(String message) {
        super(message);
    }
}

