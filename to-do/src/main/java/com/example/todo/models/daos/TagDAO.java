package com.example.todo.models.daos;

import com.example.todo.models.entities.Tag;

public record TagDAO(
        Long id,
        String name,
        String color
) {

    public TagDAO(Tag tag) {

        this(
                tag.getId(),
                tag.getName(),
                tag.getColor()
        );
    }
}
