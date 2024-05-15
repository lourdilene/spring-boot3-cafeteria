package com.example.cafeteria.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.cafeteria.dtos.UserRecordDto;
import com.example.cafeteria.models.UserModel;

public interface UserInterface {
    UserModel saveUser(UserRecordDto userRecordDto);
    List<UserModel> getAllUsers();
    Optional<UserModel> getUser(UUID id);
    UserModel updateUser(UUID id, UserRecordDto userRecordDto);
    Void deleteUser(UUID id);
}

