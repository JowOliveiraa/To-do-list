package com.example.todo.models.daos;

import com.example.todo.models.entities.Task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record TaskDAO(
        Long id,
        String name,
        String description,
        LocalDateTime registerAt,
        List<CommentDAO> comments
) {
    public TaskDAO(Task task) {

        this(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getRegisterAt(),
                task.getComments().stream().map(CommentDAO::new).collect(Collectors.toList())
        );
    }
}
