package com.example.lio.repositories;

import com.example.lio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Magic Spring Boot method to find a user by their email
    Optional<User> findByEmail(String email);
}