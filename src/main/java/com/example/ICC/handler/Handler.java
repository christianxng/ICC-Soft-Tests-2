package com.example.ICC.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class Handler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValid(MethodArgumentNotValidException e){
        List<Error> erros=e.getFieldErrors().stream().map(Error::new).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<?> MethodArgumentNotValid(NoResultException e){
        Error error = new Error("Field name not found","name" );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
