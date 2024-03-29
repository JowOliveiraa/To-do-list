package com.example.todo.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record SetStatusDTO(
        @NotBlank
        Long taskId,
        @NotBlank
        Long statusId
) {
}
