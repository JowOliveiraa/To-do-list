package com.example.todo.controllers;

import com.example.todo.models.daos.StatusDAO;
import com.example.todo.models.dtos.SetStatusDTO;
import com.example.todo.models.dtos.StatusDTO;
import com.example.todo.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("status")
public class StatusController {

    @Autowired
    private StatusService service;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody StatusDTO dto) {
        return service.register(dto);
    }

    @PostMapping("/set")
    public ResponseEntity<Object> setStatus(@RequestBody SetStatusDTO dto) {
        return service.setStatus(dto);
    }

    @GetMapping
    public ResponseEntity<List<StatusDAO>> listAllStatus(@RequestParam(required = false) String search) {
        return service.search(search);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable Long id, @RequestBody StatusDTO dto) {
        return service.updateStatus(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStatusById(@PathVariable Long id) {
        return service.deleteStatusById(id);
    }
}
