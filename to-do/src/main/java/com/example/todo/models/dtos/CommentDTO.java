package com.example.todo.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentDTO(
        @NotBlank
        String comment,
        @NotNull
        Long userId,
        @NotNull
        Long taskId
) {
}
