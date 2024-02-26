package com.example.todo.services;

import com.example.todo.models.daos.StatusDAO;
import com.example.todo.models.dtos.SetStatusDTO;
import com.example.todo.models.dtos.StatusDTO;
import com.example.todo.models.entities.Status;
import com.example.todo.repositories.StatusRepository;
import com.example.todo.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StatusService {

    @Autowired
    private StatusRepository repository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public ResponseEntity<Object> register(StatusDTO dto) {

        var status = new Status(dto);
        repository.save(status);

        return ResponseEntity.status(HttpStatus.CREATED).body(new StatusDAO(status));
    }

    @Transactional
    public ResponseEntity<Object> setStatus(SetStatusDTO dto) {

        var status = repository.getReferenceById(dto.status());
        var task = taskRepository.getReferenceById(dto.task());

        task.setStatus(status);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<List<StatusDAO>> search(String search) {

        List<Status> status = null;

        if (Objects.isNull(search)) status = repository.findAll();
        else status = repository.searchStatusByName(search);

        List<StatusDAO> listedStatus = status.stream().map(StatusDAO::new).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(listedStatus);
    }

    @Transactional
    public ResponseEntity<Object> updateStatus(Long id, StatusDTO dto) {

        var status = repository.getReferenceById(id);
        status.update(dto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StatusDAO(status));
    }

    @Transactional
    public ResponseEntity<Object> deleteStatusById(Long id) {

        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
