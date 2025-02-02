package com.microservices.resourceserver.exception.handler;

import com.microservices.resourceserver.exception.Mp3DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Mp3DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSongMetadataNotFoundException(Mp3DataNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), NOT_FOUND.value());
        return ResponseEntity.status(NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(ex.getResponseBodyAsString());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Unexpected error occurred: " + ex.getMessage());
    }
}
