package com.example.todo.repositories;

import com.example.todo.models.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM status WHERE name LIKE %?1%")
    List<Status> searchStatusByName(String search);
}
