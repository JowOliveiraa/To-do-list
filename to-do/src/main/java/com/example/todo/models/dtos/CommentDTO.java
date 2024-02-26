package com.example.todo.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record CommentDTO(
        @NotBlank
        String comment,
        @NotBlank
        Long user,
        @NotBlank
        Long task
) {
}
