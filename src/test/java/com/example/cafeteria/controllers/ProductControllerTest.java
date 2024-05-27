package com.example.cafeteria.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cafeteria.dtos.ProductRecordDto;
import com.example.cafeteria.models.ProductModel;
import com.example.cafeteria.repositories.OrderProductRepository;
import com.example.cafeteria.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @BeforeEach
    public void setup() {
        orderProductRepository.deleteAll(); 
        productRepository.deleteAll();      
    }

    @Test
    public void testSaveProduct() throws Exception {
        ProductRecordDto productDto = new ProductRecordDto("Test Product", new BigDecimal("100.00"));

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        ProductModel product = new ProductModel();
        product.setName("Test Product");
        product.setValue(new BigDecimal("100.00"));
        productRepository.save(product);

        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product"));
    }

    @Test
    public void testGetOneProduct() throws Exception {
        ProductModel product = new ProductModel();
        product.setName("Get One Product");
        product.setValue(new BigDecimal("100.00"));
        product = productRepository.save(product);

        mockMvc.perform(get("/products/{id}", product.getIdProduct())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Get One Product"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        ProductModel product = new ProductModel();
        product.setName("Old Product");
        product.setValue(new BigDecimal("100.00"));
        product = productRepository.save(product);

        ProductRecordDto productDto = new ProductRecordDto("Updated Product", new BigDecimal("150.00"));

        mockMvc.perform(put("/products/{id}", product.getIdProduct())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.value").value(150.00));
    }
    
    @Test
    public void testDeleteProduct() throws Exception {

        ProductModel product = new ProductModel();
        product.setName("Delete Product");
        product.setValue(new BigDecimal("100.00"));
        product = productRepository.save(product);

        mockMvc.perform(delete("/products/{id}", product.getIdProduct())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  
                .andExpect(content().string("Product deleted Succefully."));  

        mockMvc.perform(get("/products/{id}", product.getIdProduct())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());  
    }

}
