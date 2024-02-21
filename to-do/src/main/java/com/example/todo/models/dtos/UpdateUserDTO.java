package com.example.todo.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(
        @NotBlank
        String name,
        @NotBlank
        String email
) {
}
