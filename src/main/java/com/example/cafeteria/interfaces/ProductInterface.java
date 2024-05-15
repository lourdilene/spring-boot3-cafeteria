package com.example.cafeteria.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.cafeteria.dtos.ProductRecordDto;
import com.example.cafeteria.models.ProductModel;

public interface ProductInterface {
	ProductModel saveProduct(ProductRecordDto productRecordDto);
    List<ProductModel> getAllProducts();
    Optional<ProductModel> getOneProduct(UUID id);
    ProductModel updateProduct(UUID id, ProductRecordDto productRecordDto);
}
