package com.example.cafeteria.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cafeteria.dtos.OrderRecordDto;
import com.example.cafeteria.dtos.ProductRecordDto;
import com.example.cafeteria.interfaces.OrderInterface;
import com.example.cafeteria.models.OrderModel;

import jakarta.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    private OrderInterface orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderModel> createOrder(@RequestBody @Valid OrderRecordDto orderRecordDto) {
        OrderModel createdOrder = orderService.createOrder(orderRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderModel>> getAllOrders() {
        List<OrderModel> orders = orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable(value = "id") UUID id) {
        Optional<OrderModel> orderOptional = orderService.getOrderById(id);
        if (orderOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderOptional.get());
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Object> updateOrder(@PathVariable(value = "id") UUID id, @RequestBody @Valid OrderRecordDto orderRecordDto) {
        OrderModel updatedOrder = orderService.updateOrder(id, orderRecordDto);
        return ResponseEntity.ok(updatedOrder);
    }
    
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Object> deleteOrderById(@PathVariable(value = "id") UUID id) {
    	
        boolean isDeleted = orderService.deleteOrderById(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Order deleted successfully.");
    }
}
