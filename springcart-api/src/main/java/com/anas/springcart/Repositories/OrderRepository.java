package com.anas.springcart.Repositories;

import com.anas.springcart.Models.OrderModel;
import com.anas.springcart.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
    List<OrderModel> findByUser(UserModel user);
}