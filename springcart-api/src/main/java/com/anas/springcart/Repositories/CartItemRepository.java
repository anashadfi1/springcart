package com.anas.springcart.Repositories;

import com.anas.springcart.Models.CartItem;
import com.anas.springcart.Models.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCart(CartModel cart);
}