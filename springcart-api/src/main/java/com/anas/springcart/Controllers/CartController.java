package com.anas.springcart.Controllers;

import com.anas.springcart.Config.OwnershipValidator;
import com.anas.springcart.DTO.CartDto;
import com.anas.springcart.DTO.CartItemDto;
import com.anas.springcart.Services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('BUYER', 'ADMIN')")
public class CartController {

    private final CartService cartService;
    private final OwnershipValidator ownershipValidator;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable Integer userId) {
        ownershipValidator.validateUserOwnership(userId); // ✅ only own cart
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartDto> addItem(@PathVariable Integer cartId,
                                           @RequestBody CartItemDto itemDto) {
        ownershipValidator.validateCartOwnership(cartId); // ✅ only own cart
        return ResponseEntity.ok(cartService.addItemToCart(cartId, itemDto));
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<CartDto> removeItem(@PathVariable Integer cartId,
                                              @PathVariable Integer itemId) {
        ownershipValidator.validateCartOwnership(cartId); // ✅ only own cart
        return ResponseEntity.ok(cartService.removeItemFromCart(cartId, itemId));
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable Integer cartId) {
        ownershipValidator.validateCartOwnership(cartId); // ✅ only own cart
        cartService.clearCart(cartId);
        return ResponseEntity.noContent().build();
    }
}