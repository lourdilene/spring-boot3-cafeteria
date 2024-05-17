package com.example.cafeteria.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cafeteria.models.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, UUID> {

}

