package com.example.todo.repositories;

import com.example.todo.models.entities.Tag;
import com.example.todo.models.entities.Task;
import com.example.todo.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    boolean existsByStatusId(Long id);

    List<Task> findAllByTagsContains(Tag tag);

    List<Task> findAllByResponsibleContains(User user);
}
