package com.example.todo.models.daos;

import com.example.todo.models.entities.Comment;

import java.time.LocalDateTime;

public record CommentDAO(
        Long id,
        String comment,
        LocalDateTime registerAt,
        UserDAO user
) {

    public CommentDAO(Comment comment) {

        this(
                comment.getId(),
                comment.getComment(),
                comment.getRegisterAt(),
                new UserDAO(comment.getUser())
        );
    }
}
