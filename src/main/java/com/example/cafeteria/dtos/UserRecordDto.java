package com.example.cafeteria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRecordDto(@NotBlank String email, @NotNull String password, @NotBlank String role) {

}
