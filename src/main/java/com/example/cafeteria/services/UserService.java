package com.example.cafeteria.services;

//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cafeteria.dtos.UserRecordDto;
import com.example.cafeteria.interfaces.UserInterface;
import com.example.cafeteria.models.UserModel;
import com.example.cafeteria.repositories.UserRepository;

@Service
public class UserService implements UserInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel saveUser(UserRecordDto userRecordDto) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        
        // Definindo a data de criação
//        userModel.setCreatedAt(LocalDateTime.now());
        
        return userRepository.save(userModel);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> getUser(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public UserModel updateUser(UUID id, UserRecordDto userRecordDto) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }
        UserModel existingUser = userOptional.get();
        BeanUtils.copyProperties(userRecordDto, existingUser);
        
        // Definindo a data de atualização
//        existingUser.setUpdatedAt(LocalDateTime.now());
        
        return userRepository.save(existingUser);
    }
    
    @Override
    public Void deleteUser(UUID id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) {
            throw new RuntimeException("User not found.");
        }
        userRepository.delete(userOptional.get());
        return null;
    }
}
