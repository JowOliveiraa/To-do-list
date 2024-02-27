package com.example.todo.models.dtos;

import jakarta.validation.constraints.NotBlank;

public record SetResponsibleDTO(
        @NotBlank
        Long taskId,
        @NotBlank
        Long responsibleId
) {
}
