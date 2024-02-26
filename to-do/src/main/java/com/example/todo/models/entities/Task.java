package com.example.todo.models.entities;

import com.example.todo.models.dtos.TaskDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @CreationTimestamp
    private LocalDateTime registerAt;

    @Setter
    @ManyToOne
    private Status status;

    @OneToMany(mappedBy = "task", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    public Task(TaskDTO dto) {

        this.name = dto.name();
        this.description = dto.description();
    }

    public void update(TaskDTO dto) {

        this.name = dto.name();
        this.description = dto.description();
    }
}
