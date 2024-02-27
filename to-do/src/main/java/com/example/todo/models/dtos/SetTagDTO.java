package com.example.todo.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record SetTagDTO(
        @NotBlank
        Long taskId,
        @NotBlank
        Long tagId
) {
}
