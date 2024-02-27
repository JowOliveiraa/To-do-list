package com.example.todo.controllers;

import com.example.todo.models.daos.TaskDAO;
import com.example.todo.models.dtos.SetResponsibleDTO;
import com.example.todo.models.dtos.TaskDTO;
import com.example.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody TaskDTO dto) {
        return service.register(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getTaskById(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @GetMapping
    public Page<TaskDAO> listAllTasks(@PageableDefault(size = 10, page = 0)Pageable pageable) {
        return service.listAllTasks(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Long id, @RequestBody TaskDTO dto) {
        return service.updateTask(id, dto);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteTaskById(@PathVariable Long id) {
        return service.deleteTaskById(id);
    }

    @PostMapping("/responsible/add")
    public ResponseEntity<Object> addResponsible(@RequestBody SetResponsibleDTO dto) {
        return service.addResponsible(dto);
    }

    @DeleteMapping("/responsible/remove")
    public ResponseEntity<Object> removeResponsible(@RequestBody SetResponsibleDTO dto) {
        return service.removeResponsible(dto);
    }
}
