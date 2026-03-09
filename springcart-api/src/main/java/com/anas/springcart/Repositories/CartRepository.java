package com.anas.springcart.Repositories;

import com.anas.springcart.Models.CartModel;
import com.anas.springcart.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Integer> {
    Optional<CartModel> findByUser(UserModel user);
}