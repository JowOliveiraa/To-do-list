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
        StatusDAO status,
        List<TagDAO> tags,
        List<CommentDAO> comments
) {
    public TaskDAO(Task task) {

        this(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getRegisterAt(),
                new StatusDAO(task.getStatus()),
                task.getTags().stream().map(TagDAO::new).toList(),
                task.getComments().stream().map(CommentDAO::new).toList()
        );
    }
}
