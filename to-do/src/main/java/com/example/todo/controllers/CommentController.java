package com.example.todo.controllers;

import com.example.todo.models.dtos.CommentDTO;
import com.example.todo.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    public ResponseEntity<Object> register(@Valid @RequestBody CommentDTO dto) {
        return service.register(dto);
    }
}
