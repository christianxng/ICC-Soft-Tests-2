package com.example.ICC.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.FieldError;

public class Error {
   @JsonProperty
    private final String message;
    @JsonProperty
    private final String field;

    public Error(FieldError error) {
        this.message = error.getDefaultMessage();
        this.field = error.getField();
    }

    public Error(String message, String field) {
        this.message = message;
        this.field = field;
    }
}
