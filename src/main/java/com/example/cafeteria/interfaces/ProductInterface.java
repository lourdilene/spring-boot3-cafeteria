package com.example.cafeteria.interfaces;

import com.example.cafeteria.dtos.ProductRecordDto;
import com.example.cafeteria.models.ProductModel;

public interface ProductInterface {
	ProductModel saveProduct(ProductRecordDto productRecordDto);
}
