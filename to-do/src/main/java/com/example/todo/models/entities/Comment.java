package com.example.todo.models.entities;

import com.example.todo.models.dtos.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @CreationTimestamp
    private LocalDateTime registerAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne
    private Task task;

    public Comment(CommentDTO dto, User user, Task task) {

        this.comment = dto.comment();
        this.user = user;
        this.task = task;
    }
}
