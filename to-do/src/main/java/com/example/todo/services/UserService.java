package com.example.todo.services;

import com.example.todo.models.daos.LoginDAO;
import com.example.todo.models.daos.UserDAO;
import com.example.todo.models.dtos.LoginDTO;
import com.example.todo.models.dtos.UpdateUserDTO;
import com.example.todo.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.models.dtos.UserDTO;
import com.example.todo.models.entities.User;
import com.example.todo.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TokenService tokenService;

    private AuthenticationManager authManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

    @Transactional
    public ResponseEntity<Object> register(UserDTO dto) {

        if (repository.findByEmail(dto.email())  != null) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email ja cadastrado!");
        }

        var user = new User(dto);

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        user.setPassword(encryptedPassword);

        repository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso!");
    }

    public ResponseEntity<Object> login(LoginDTO dto) {

        authManager = context.getBean(AuthenticationManager.class);

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        var user = (User) repository.findByEmail(dto.email());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new LoginDAO(user, token));
    }

    public ResponseEntity<Object> getById(Long id) {

        var user = repository.getReferenceById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new UserDAO(user));
    }

    public Page<UserDAO> search(Pageable pageable, String search) {

        Page<User> users = null;

        if (Objects.isNull(search)) users = repository.findAll(pageable);
        else users = repository.search(pageable, search);

        List<UserDAO> pagedUsers = users.getContent().stream().map(UserDAO::new).collect(Collectors.toList());

        return new PageImpl<>(pagedUsers, pageable, users.getTotalElements());
    }

    @Transactional
    public ResponseEntity<Object> update(Long id, UpdateUserDTO dto) {

        var user = repository.getReferenceById(id);
        user.update(dto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserDAO(user));
    }

    @Transactional
    public ResponseEntity<Object> status(Long id, boolean active) {

        var user = repository.getReferenceById(id);
        user.setActive(active);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
