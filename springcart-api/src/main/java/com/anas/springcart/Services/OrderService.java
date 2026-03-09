package com.anas.springcart.Services;

import com.anas.springcart.DTO.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(Integer userId); // checkout current cart
    List<OrderDto> getOrdersByUserId(Integer userId);
    OrderDto getOrderById(Integer orderId);
}