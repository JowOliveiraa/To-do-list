package com.example.todo.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.TransientPropertyValueException;
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

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointer(NullPointerException exception) {

        ErrorDAO error = new ErrorDAO(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

        return ResponseEntity.status(error.status()).body(error);
    }

    @ExceptionHandler(TransientPropertyValueException.class)
    public ResponseEntity<Object> transientValue(TransientPropertyValueException exception) {

        ErrorDAO error = new ErrorDAO(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

        return ResponseEntity.status(error.status()).body(error);
    }

//    @Override
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode statusId, WebRequest request) {
//
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//
//        return this.handleExceptionInternal(ex, errors, headers, statusId, request);
//    }
}
