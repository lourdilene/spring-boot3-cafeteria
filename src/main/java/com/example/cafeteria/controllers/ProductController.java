package com.example.cafeteria.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeteria.dtos.ProductRecordDto;
import com.example.cafeteria.interfaces.ProductInterface;
import com.example.cafeteria.models.ProductModel;
import com.example.cafeteria.repositories.ProductRepository;
import com.example.cafeteria.services.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
	
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        ProductModel savedProduct = productService.saveProduct(productRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
	
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") UUID id) {
        Optional<ProductModel> productOptional = productService.getOneProduct(id);
        if(productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
            @RequestBody @Valid ProductRecordDto productRecordDto) {
        ProductModel updatedProduct = productService.updateProduct(id, productRecordDto);
        return ResponseEntity.ok(updatedProduct);
    }

}
