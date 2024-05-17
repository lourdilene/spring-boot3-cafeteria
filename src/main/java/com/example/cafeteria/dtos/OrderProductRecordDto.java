package com.example.cafeteria.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record OrderProductRecordDto(        
		@NotNull int qty,
        @NotNull UUID idProduct
) {

	public int getQty() {
		return qty;
	}

	public UUID getIdProduct() {
		return idProduct;
	}
	
	
}
