package com.anas.springcart.Services;

import com.anas.springcart.DTO.CartDto;
import com.anas.springcart.DTO.CartItemDto;

public interface CartService {

    CartDto getCartByUserId(Integer userId); // assuming you will link carts to users later
    CartDto addItemToCart(Integer cartId, CartItemDto itemDto);
    CartDto removeItemFromCart(Integer cartId, Integer itemId);
    void clearCart(Integer cartId);
}
