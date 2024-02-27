package com.example.todo.services;

import com.example.todo.models.daos.TagDAO;
import com.example.todo.models.dtos.SetTagDTO;
import com.example.todo.models.dtos.TagDTO;
import com.example.todo.models.entities.Tag;
import com.example.todo.repositories.TagRepository;
import com.example.todo.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public ResponseEntity<Object> register(TagDTO dto) {

        var tag = new Tag(dto);
        repository.save(tag);

        return ResponseEntity.status(HttpStatus.CREATED).body(new TagDAO(tag));
    }

    @Transactional
    public ResponseEntity<Object> setTag(SetTagDTO dto) {

        var tag = repository.getReferenceById(dto.tagId());
        var task = taskRepository.getReferenceById(dto.taskId());

        task.getTags().add(tag);
        taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public Page<TagDAO> list(Pageable pageable) {

        var tags = repository.findAll(pageable);
        var tagsList = tags.getContent().stream().map(TagDAO::new).toList();

        return new PageImpl<>(tagsList, pageable, tags.getTotalElements());
    }

    @Transactional
    public ResponseEntity<Object> updateTag(Long id, TagDTO dto) {

        var tag = repository.getReferenceById(id);
        tag.update(dto);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new TagDAO(tag));
    }

    @Transactional
    public ResponseEntity<Object> removeTagFromTask(SetTagDTO dto) {

        var tag = repository.getReferenceById(dto.tagId());
        var task = taskRepository.getReferenceById(dto.taskId());

        task.getTags().remove(tag);
        taskRepository.save(task);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity<Object> deleteTagById(Long id) {

        var tag = repository.getReferenceById(id);
        var tasks = taskRepository.findAllByTagsContains(tag);

        tasks.forEach(task -> {

            task.getTags().remove(tag);

        });

        repository.delete(tag);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
