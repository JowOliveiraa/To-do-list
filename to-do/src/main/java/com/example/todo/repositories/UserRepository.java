package com.example.todo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.todo.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    UserDetails findByEmail(String username);

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE name LIKE %?1% OR email LIKE %?1%")
    Page<User> search(Pageable pageable, String search);
}
