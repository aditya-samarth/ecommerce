package com.project.ecommerce.repository;

import com.project.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
}
