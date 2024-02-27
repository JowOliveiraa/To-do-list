package com.example.todo.models.entities;

import com.example.todo.models.dtos.TagDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String color;

    public Tag(TagDTO dto) {

        this.name = dto.name();
        this.color = dto.color();
    }

    public void update(TagDTO dto) {

        this.name = dto.name();
        this.color = dto.color();
    }
}
