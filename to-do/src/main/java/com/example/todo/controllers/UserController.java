package com.example.todo.controllers;

import com.example.todo.models.daos.UserDAO;
import com.example.todo.models.dtos.LoginDTO;
import com.example.todo.models.dtos.UpdateUserDTO;
import com.example.todo.models.dtos.UserDTO;
import com.example.todo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody @Valid UserDTO dto) {

        return service.register(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO dto) {

        return service.login(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {

        return service.getById(id);
    }

    @GetMapping
    public Page<UserDAO> listAllUsers(@PageableDefault(size = 10, page = 0)Pageable pageable,
                                      @RequestParam(required = false)String search) {

        return service.search(pageable, search);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody @Valid UpdateUserDTO dto) {

        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> status(@PathVariable Long id, @RequestParam boolean active) {

        return service.status(id, active);
    }
}
