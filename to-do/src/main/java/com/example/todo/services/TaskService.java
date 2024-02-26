package com.example.todo.services;

import com.example.todo.models.daos.TaskDAO;
import com.example.todo.models.dtos.TaskDTO;
import com.example.todo.models.entities.Status;
import com.example.todo.models.entities.Task;
import com.example.todo.repositories.StatusRepository;
import com.example.todo.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private StatusRepository statusRepository;

    @Transactional
    public ResponseEntity<Object> register(TaskDTO dto) {

        var task = new Task(dto);

        task.setStatus(returnDefaltStatus());
        repository.save(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TaskDAO(task));
    }

    public ResponseEntity<Object> getTaskById(Long id) {

        var task = repository.getReferenceById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new TaskDAO(task));
    }

    public Page<TaskDAO> listAllTasks(Pageable pageable) {

        var tasks = repository.findAll(pageable);

        List<TaskDAO> tasksList = tasks.stream().map(TaskDAO::new).collect(Collectors.toList());

        return new PageImpl<>(tasksList, pageable, tasks.getTotalElements());
    }

    @Transactional
    public ResponseEntity<Object> updateTask(Long id, TaskDTO dto) {

        var task = repository.getReferenceById(id);
        task.update(dto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new TaskDAO(task));
    }

    @Transactional
    public ResponseEntity<Object> deleteTaskById(Long id) {

        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Tarefa deletada!");
    }

    public Status returnDefaltStatus() {

        return statusRepository.getReferenceById(1L);
    }
}
