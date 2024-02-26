package com.example.todo.models.daos;

import com.example.todo.models.entities.Status;

public record StatusDAO(
        Long id,
        String name,
        String color
) {
    public StatusDAO(Status status) {

        this(
                status.getId(),
                status.getName(),
                status.getColor()
        );
    }
}
