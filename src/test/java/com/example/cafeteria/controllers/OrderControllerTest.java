package com.example.cafeteria.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.cafeteria.dtos.OrderProductRecordDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cafeteria.dtos.OrderRecordDto;
import com.example.cafeteria.models.OrderModel;
import com.example.cafeteria.models.OrderProductModel;
import com.example.cafeteria.models.ProductModel;
import com.example.cafeteria.models.UserModel;
import com.example.cafeteria.repositories.OrderProductRepository;
import com.example.cafeteria.repositories.OrderRepository;
import com.example.cafeteria.repositories.ProductRepository;
import com.example.cafeteria.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        orderProductRepository.deleteAll();
        orderRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void testCreateOrder() throws Exception {
        UserModel user = new UserModel();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setRole("CUSTOMER");
        user = userRepository.save(user);

        ProductModel product = new ProductModel();
        product.setName("Product 1");
        product.setValue(new BigDecimal("100.00"));
        product = productRepository.save(product);

        List<OrderProductRecordDto> products = new ArrayList<>();
        products.add(new OrderProductRecordDto(2, product.getIdProduct()));

        OrderRecordDto orderDto;
        orderDto = new OrderRecordDto(user.getIdUser(), products, "PENDING", LocalDateTime.now());

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    public void testGetAllOrders() throws Exception {
        UserModel user = new UserModel();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setRole("CUSTOMER");
        user = userRepository.save(user);

        OrderModel order = new OrderModel();
        order.setUserModel(user);
        order.setStatus("PENDING");
        order.setDateEntry(LocalDateTime.now());
        order = orderRepository.save(order);

        mockMvc.perform(get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status").value("PENDING"));
    }

    @Test
    public void testGetOrderById() throws Exception {
        UserModel user = new UserModel();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setRole("CUSTOMER");
        user = userRepository.save(user);

        OrderModel order = new OrderModel();
        order.setUserModel(user);
        order.setStatus("PENDING");
        order.setDateEntry(LocalDateTime.now());
        order = orderRepository.save(order);

        mockMvc.perform(get("/orders/{id}", order.getIdOrders())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        UserModel user = new UserModel();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setRole("CUSTOMER");
        user = userRepository.save(user);

        OrderModel order = new OrderModel();
        order.setUserModel(user);
        order.setStatus("PENDING");
        order.setDateEntry(LocalDateTime.now());
        order = orderRepository.save(order);

        List<OrderProductRecordDto> products = new ArrayList<>();

        OrderRecordDto orderDto = new OrderRecordDto(user.getIdUser(), products, "COMPLETED", LocalDateTime.now());

        mockMvc.perform(put("/orders/{id}", order.getIdOrders())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("COMPLETED"));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        UserModel user = new UserModel();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setRole("CUSTOMER");
        user = userRepository.save(user);

        OrderModel order = new OrderModel();
        order.setUserModel(user);
        order.setStatus("PENDING");
        order.setDateEntry(LocalDateTime.now());
        order = orderRepository.save(order);

        mockMvc.perform(delete("/orders/{id}", order.getIdOrders())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Order deleted successfully."));

        mockMvc.perform(get("/orders/{id}", order.getIdOrders())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
