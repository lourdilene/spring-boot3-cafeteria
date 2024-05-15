package com.example.cafeteria.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeteria.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

}
