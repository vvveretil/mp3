package com.microservices.resourceserver.exception;

public class Mp3DataNotFoundException extends RuntimeException {
    public Mp3DataNotFoundException(String message) {
        super(message);
    }
}
