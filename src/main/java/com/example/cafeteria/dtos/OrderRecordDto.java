package com.example.cafeteria.dtos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRecordDto(
        @NotNull UUID idUser,
        @NotNull List<OrderProductRecordDto> productsQty,
        @NotBlank String status,
        @NotNull LocalDateTime dateEntry
) {

	public UUID getIdUser() {
		return idUser;
	}

	public List<OrderProductRecordDto> getProductsQty() {
		return productsQty;
	}

	public String getStatus() {
		return status;
	}

	public LocalDateTime getDateEntry() {
		return dateEntry;
	}
}
