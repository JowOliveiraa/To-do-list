package com.example.todo.models.daos;

import com.example.todo.models.entities.User;
import com.example.todo.models.enums.Role;
import org.springframework.security.core.userdetails.UserDetails;

public record LoginDAO(
        String name,
        Role role,
        String token
) {
    public LoginDAO(User user, String token) {

        this(
                user.getName(),
                user.getRole(),
                token
        );
    }
}
