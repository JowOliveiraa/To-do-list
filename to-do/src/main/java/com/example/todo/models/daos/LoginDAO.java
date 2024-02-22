package com.example.todo.models.daos;

import com.example.todo.models.entities.User;
import com.example.todo.models.enums.Role;

public record LoginDAO(
        Long id,
        String name,
        Role role,
        String token
) {
    public LoginDAO(User user, String token) {

        this(
                user.getId(),
                user.getName(),
                user.getRole(),
                token
        );
    }
}
