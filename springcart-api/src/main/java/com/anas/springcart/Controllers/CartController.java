package com.anas.springcart.Controllers;

import com.anas.springcart.DTO.CartDto;
import com.anas.springcart.DTO.CartItemDto;
import com.anas.springcart.Services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // --- Get cart by user ---
    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Integer userId) {
        CartDto cart = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // --- Add item to cart ---
    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartDto> addItem(@PathVariable Integer cartId,
                                           @RequestBody CartItemDto itemDto) {
        CartDto cart = cartService.addItemToCart(cartId, itemDto);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // --- Remove item from cart ---
    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartDto> removeItem(@PathVariable Integer cartId,
                                              @PathVariable Integer itemId) {
        CartDto cart = cartService.removeItemFromCart(cartId, itemId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    // --- Clear cart ---
    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Integer cartId) {
        cartService.clearCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}