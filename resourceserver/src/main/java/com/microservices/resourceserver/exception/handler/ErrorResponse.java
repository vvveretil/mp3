package com.microservices.resourceserver.exception.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String message;
    private int code;
    private Map<String, String> details;

    public ErrorResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
