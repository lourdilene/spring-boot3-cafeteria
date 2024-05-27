package com.example.cafeteria.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cafeteria.dtos.UserRecordDto;
import com.example.cafeteria.models.UserModel;
import com.example.cafeteria.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        userRepository.deleteAll();      
    }

    @Test
    public void testSaveUser() throws Exception {
        UserRecordDto userDto = new UserRecordDto("john.doe@example.com", "password123", "USER");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.password").value("password123"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        UserModel user = new UserModel();
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setRole("USER");
        userRepository.save(user);

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].password").value("password123"))
                .andExpect(jsonPath("$[0].role").value("USER"));
    }

    @Test
    public void testGetOneUser() throws Exception {
        UserModel user = new UserModel();
        user.setEmail("jane.doe@example.com");
        user.setPassword("password456");
        user.setRole("ADMIN");
        user = userRepository.save(user);

        mockMvc.perform(get("/users/{id}", user.getIdUser())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("jane.doe@example.com"))
                .andExpect(jsonPath("$.password").value("password456"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserModel user = new UserModel();
        user.setEmail("old.email@example.com");
        user.setPassword("oldpassword");
        user.setRole("USER");
        user = userRepository.save(user);

        UserRecordDto userDto = new UserRecordDto("new.email@example.com", "newpassword", "ADMIN");

        mockMvc.perform(put("/users/{id}", user.getIdUser())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("new.email@example.com"))
                .andExpect(jsonPath("$.password").value("newpassword"))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    public void testDeleteUser() throws Exception {
        UserModel user = new UserModel();
        user.setEmail("delete.email@example.com");
        user.setPassword("deletepassword");
        user.setRole("USER");
        user = userRepository.save(user);

        mockMvc.perform(delete("/users/{id}", user.getIdUser())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted Succefully."));

        mockMvc.perform(get("/users/{id}", user.getIdUser())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
