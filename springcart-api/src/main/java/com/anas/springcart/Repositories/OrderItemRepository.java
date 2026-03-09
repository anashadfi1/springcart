package com.anas.springcart.Repositories;

import com.anas.springcart.Models.OrderItem;
import com.anas.springcart.Models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByOrder(OrderModel order);
}