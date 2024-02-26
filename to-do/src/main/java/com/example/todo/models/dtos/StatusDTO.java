package com.example.todo.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record StatusDTO(
        @NotBlank
        String name,
        @NotBlank
        String color
) {
}
