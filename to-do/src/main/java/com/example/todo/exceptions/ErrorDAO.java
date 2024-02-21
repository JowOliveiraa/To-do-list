package com.example.todo.exceptions;

public record ErrorDAO(
        org.springframework.http.HttpStatusCode status,
        String message
) {
}
