package com.example.todo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/user").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/user/{id}", "/user").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/user/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/user/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/task", "task/responsible/add").authenticated()
                        .requestMatchers(HttpMethod.GET, "/task/{id}", "/task").authenticated()
                        .requestMatchers(HttpMethod.POST, "/comment").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/task/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/task/responsible/remove").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/task/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/status", "/status/set").authenticated()
                        .requestMatchers(HttpMethod.GET, "/status").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/status/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/status/{id}").authenticated()
                        .requestMatchers(HttpMethod.POST, "/tag", "/tag/set").authenticated()
                        .requestMatchers(HttpMethod.GET, "/tag").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/tag/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/tag/{id}", "/tag/remove").authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
