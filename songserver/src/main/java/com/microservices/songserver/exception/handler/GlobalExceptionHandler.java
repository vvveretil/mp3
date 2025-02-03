package com.microservices.songserver.exception.handler;

import com.microservices.songserver.exception.SongMetadataNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(SongMetadataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSongMetadataNotFoundException(SongMetadataNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), NOT_FOUND.value());
        return ResponseEntity.status(NOT_FOUND).body(errorResponse);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> details = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.put(error.getField(), error.getDefaultMessage());
        }
        
        ErrorResponse errorResponse = new ErrorResponse("Validation error", BAD_REQUEST.value(), details);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
