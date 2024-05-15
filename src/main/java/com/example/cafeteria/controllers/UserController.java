package com.example.cafeteria.controllers;

import com.example.cafeteria.dtos.UserRecordDto;
import com.example.cafeteria.interfaces.UserInterface;
import com.example.cafeteria.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserInterface userService;

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        UserModel savedUser = userService.saveUser(userRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value="id") UUID id) {
        Optional<UserModel> userOptional = userService.getUser(id);
        if(userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value="id") UUID id,
                                             @RequestBody @Valid UserRecordDto userRecordDto) {
        UserModel updatedUser = userService.updateUser(id, userRecordDto);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value="id") UUID id) {
        Optional<UserModel> userOptional = userService.getUser(id);
        if(userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User deleted Succefully.");
    }
}
