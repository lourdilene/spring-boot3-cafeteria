package com.example.cafeteria.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cafeteria.dtos.ProductRecordDto;
import com.example.cafeteria.interfaces.ProductInterface;
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
        return productRepository.save(productModel);
    }

//    @Override
//    public List<ProductModel> getAllProducts() {
//        // Implemente a lógica para obter todos os produtos
//    }
//
//    @Override
//    public Optional<ProductModel> getOneProduct(UUID id) {
//        // Implemente a lógica para obter um produto específico
//    }
//
//    @Override
//    public ProductModel updateProduct(UUID id, ProductRecordDto productRecordDto) {
//        // Implemente a lógica para atualizar um produto
//    }
//
//	@Override
//	public Optional<ProductModel> getOneProduct(UUID id) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public ProductModel updateProduct(UUID id, ProductRecordDto productRecordDto) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

