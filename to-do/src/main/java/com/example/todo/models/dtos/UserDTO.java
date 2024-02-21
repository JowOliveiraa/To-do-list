package com.example.todo.models.dtos;

import com.example.todo.models.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
    @NotBlank
    String name,
    @NotBlank
    String password,
    @NotBlank
    String email,
    @NotNull
    Role role
) {}
