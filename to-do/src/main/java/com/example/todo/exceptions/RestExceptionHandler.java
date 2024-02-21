package com.example.todo.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> badRequest(DataIntegrityViolationException exception) {

        ErrorDAO error = new ErrorDAO(HttpStatus.BAD_REQUEST, "Verifique a requisição");

        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> notFound(EntityNotFoundException exception) {

        ErrorDAO error = new ErrorDAO(HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(error.status()).body(error);
    }
}
