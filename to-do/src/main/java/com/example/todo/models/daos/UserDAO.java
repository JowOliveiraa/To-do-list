package com.example.todo.models.daos;

import com.example.todo.models.entities.User;
import com.example.todo.models.enums.Role;

import java.time.LocalDateTime;

public record UserDAO(
        Long id,
        String name,
        String email,
        boolean active,
        Role role,
        LocalDateTime registerAt
) {
    public UserDAO(User user) {

        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isActive(),
                user.getRole(),
                user.getRegisterAt()
        );
    }
}
