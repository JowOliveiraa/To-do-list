package com.example.todo.controllers;

import com.example.todo.models.daos.TagDAO;
import com.example.todo.models.dtos.SetTagDTO;
import com.example.todo.models.dtos.TagDTO;
import com.example.todo.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tag")
public class TagController {

    @Autowired
    private TagService service;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody TagDTO dto) {
        return service.register(dto);
    }

    @PostMapping("/set")
    public ResponseEntity<Object> setTag(@RequestBody SetTagDTO dto) {
        return service.setTag(dto);
    }

    @GetMapping
    public Page<TagDAO> listAllTags(@PageableDefault(size = 10, page = 0)Pageable pageable) {
        return service.list(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTag(@PathVariable Long id, @RequestBody TagDTO dto) {
        return service.updateTag(id, dto);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Object> removeTagFromTask(@RequestBody SetTagDTO dto) {
        return service.removeTagFromTask(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTagById(@PathVariable Long id) {
        return service.deleteTagById(id);
    }
}
