package com.example.todo.models.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import com.example.todo.models.dtos.UpdateUserDTO;
import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import com.example.todo.models.dtos.UserDTO;
import com.example.todo.models.enums.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Setter
    private String password;

    private String email;

    @Setter
    private boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime registerAt;


    public User(UserDTO dto) {

        this.name = dto.name();
        this.password = dto.password();
        this.email = dto.email();
        this.active = true;
        this.role = dto.role();
    }

    public void update(UpdateUserDTO dto) {

        this.name = dto.name();
        this.email = dto.email();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.role == Role.ADMIN) {

            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_DEFAULT"));
        }
        else {

            return List.of(new SimpleGrantedAuthority("ROLE_DEFAULT"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}