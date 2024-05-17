package com.example.cafeteria.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.cafeteria.dtos.OrderRecordDto;
import com.example.cafeteria.models.OrderModel;

public interface OrderInterface {
    OrderModel createOrder(OrderRecordDto orderRecordDto);
    List<OrderModel> getAllOrders();
    Optional<OrderModel> getOrderById(UUID id);
//    OrderModel updateOrder(UUID id, OrderRecordDto orderRecordDto);
//    void deleteOrder(UUID id);
}
