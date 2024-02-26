package com.example.todo.models.entities;

import com.example.todo.models.dtos.StatusDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String color;

    public Status(StatusDTO dto) {

        this.name = dto.name();
        this.color = dto.color();
    }

    public void update(StatusDTO dto) {

        this.name = dto.name();
        this.color = dto.color();
    }
}
