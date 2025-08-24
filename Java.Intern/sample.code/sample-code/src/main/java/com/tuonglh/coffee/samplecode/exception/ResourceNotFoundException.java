package com.tuonglh.coffee.samplecode.exception;

public class ResourceNotFoundException extends RuntimeException { // bắt exception trong lúc run time

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
