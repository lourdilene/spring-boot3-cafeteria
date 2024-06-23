package com.example.cafeteria.repositories;

import com.example.cafeteria.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
