package com.example.cafeteria.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cafeteria.dtos.OrderProductRecordDto;
import com.example.cafeteria.dtos.OrderRecordDto;
import com.example.cafeteria.interfaces.OrderInterface;
import com.example.cafeteria.models.OrderModel;
import com.example.cafeteria.models.OrderProductModel;
import com.example.cafeteria.models.ProductModel;
import com.example.cafeteria.models.UserModel;
import com.example.cafeteria.repositories.OrderRepository;
import com.example.cafeteria.repositories.ProductRepository;
import com.example.cafeteria.repositories.UserRepository;

@Service
public class OrderService implements OrderInterface {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public OrderModel createOrder(OrderRecordDto orderRecordDto) {

        UserModel userModel = userRepository.findById(orderRecordDto.getIdUser())
                                            .orElseThrow(() -> new RuntimeException("User not found."));

        OrderModel orderModel = new OrderModel();
        orderModel.setUserModel(userModel);
        orderModel.setStatus(orderRecordDto.getStatus());
        orderModel.setDateEntry(orderRecordDto.getDateEntry());

        for (OrderProductRecordDto orderProductRecordDto : orderRecordDto.getProductsQty()) {

            ProductModel productModel = productRepository.findById(orderProductRecordDto.getIdProduct())
                                                         .orElseThrow(() -> new RuntimeException("Product not found."));

            OrderProductModel orderProductModel = new OrderProductModel();
            orderProductModel.setProduct(productModel);
            orderProductModel.setQty(orderProductRecordDto.getQty());

            orderModel.getProductsQty().add(orderProductModel);
        }

        return orderRepository.save(orderModel);
    }

    @Override
    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderModel> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }
//
//    @Override
//    public OrderModel updateOrder(UUID id, OrderDto orderDto) {
//        OrderModel existingOrder = orderRepository.findById(id)
//                                                  .orElseThrow(() -> new RuntimeException("Order not found."));
//
//        UserModel userModel = userRepository.findById(orderDto.getUserId())
//                                            .orElseThrow(() -> new RuntimeException("User not found."));
//        existingOrder.setUserModel(userModel);
//        existingOrder.setStatus(orderDto.getStatus());
//        existingOrder.setDateEntry(LocalDateTime.parse(orderDto.getDateEntry()));
//
//        for (OrderDto.OrderProductDto orderProductDto : orderDto.getProducts()) {
//            ProductModel productModel = productRepository.findById(orderProductDto.getProductId())
//                                                         .orElseThrow(() -> new RuntimeException("Product not found."));
//            OrderProductModel orderProductModel = new OrderProductModel();
//            orderProductModel.setProduct(productModel);
//            orderProductModel.setQty(orderProductDto.getQty());
//            existingOrder.getProducts().add(orderProductModel);
//        }
//
//        return orderRepository.save(existingOrder);
//    }
//
//    @Override
//    public void deleteOrder(UUID id) {
//        orderRepository.deleteById(id);
//    }
}

