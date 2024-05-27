package com.example.cafeteria.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cafeteria.dtos.ProductRecordDto;
import com.example.cafeteria.interfaces.ProductInterface;
import com.example.cafeteria.models.ProductModel;
import com.example.cafeteria.models.ProductModel;
import com.example.cafeteria.repositories.ProductRepository;

@Service
public class ProductService implements ProductInterface {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductModel saveProduct(ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        
        LocalDateTime currentDateTime = LocalDateTime.now();
        productModel.setDateEntry(currentDateTime);
        
        return productRepository.save(productModel);
    }

    @Override
    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<ProductModel> getOneProduct(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public ProductModel updateProduct(UUID id, ProductRecordDto productRecordDto) {
        Optional<ProductModel> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) {
            throw new RuntimeException("Product not found.");
        }
        ProductModel existingProduct = productOptional.get();
        BeanUtils.copyProperties(productRecordDto, existingProduct);
        
        return productRepository.save(existingProduct);
    }
    
    @Override
    public Void deleteProduct(UUID id) {
        Optional<ProductModel> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new RuntimeException("User not found.");
        }
        productRepository.delete(optionalProduct.get());
        return null;
    }
}

