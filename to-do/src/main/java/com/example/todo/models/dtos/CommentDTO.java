package com.example.todo.models.dtos;

public record CommentDTO(
        String comment,
        Long user,
        Long task
) {
}
