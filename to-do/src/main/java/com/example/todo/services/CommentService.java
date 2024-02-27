package com.example.todo.services;

import com.example.todo.models.daos.CommentDAO;
import com.example.todo.models.dtos.CommentDTO;
import com.example.todo.models.entities.Comment;
import com.example.todo.repositories.CommentRepository;
import com.example.todo.repositories.TaskRepository;
import com.example.todo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;


    @Transactional
    public ResponseEntity<Object> register(CommentDTO dto) {

        var task = taskRepository.getReferenceById(dto.taskId());
        var user = userRepository.getReferenceById(dto.userId());
        var comment = new Comment(dto, user, task);

        repository.save(comment);
        taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.OK).body(new CommentDAO(comment));
    }
}
