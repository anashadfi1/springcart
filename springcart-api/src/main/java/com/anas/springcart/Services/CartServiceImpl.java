package com.anas.springcart.Services;

import com.anas.springcart.DTO.CartDto;
import com.anas.springcart.DTO.CartItemDto;
import com.anas.springcart.DTO.ProductDto;
import com.anas.springcart.Models.CartItem;
import com.anas.springcart.Models.CartModel;
import com.anas.springcart.Models.ProductModel;
import com.anas.springcart.Repositories.CartItemRepository;
import com.anas.springcart.Repositories.CartRepository;
import com.anas.springcart.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public CartDto getCartByUserId(Integer userId) {
        // For now, use userId as cart ID
        CartModel cart = cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return mapToDto(cart);
    }

    @Override
    public CartDto addItemToCart(Integer cartId, CartItemDto itemDto) {
        CartModel cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        ProductModel product = productRepository.findById(itemDto.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if item already exists in cart
        CartItem existingItem = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + itemDto.getQuantity());
            cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(itemDto.getQuantity());
            cart.getItems().add(newItem);
            cartItemRepository.save(newItem);
        }

        cartRepository.save(cart);
        return mapToDto(cart);
    }

    @Override
    public CartDto removeItemFromCart(Integer cartId, Integer itemId) {
        CartModel cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cart.getItems().remove(item);
        cartItemRepository.delete(item);
        cartRepository.save(cart);

        return mapToDto(cart);
    }

    @Override
    public void clearCart(Integer cartId) {
        CartModel cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    // --- Mapping Method ---
    private CartDto mapToDto(CartModel cart) {
        return new CartDto(
                cart.getId(),
                cart.getItems() != null ?
                        cart.getItems().stream()
                                .map(i -> new CartItemDto(
                                        i.getId(),
                                        new ProductDto(
                                                i.getProduct().getId(),
                                                i.getProduct().getName(),
                                                i.getProduct().getBrand(),
                                                i.getProduct().getCategory() != null ? i.getProduct().getCategory().getName() : null,
                                                i.getProduct().getPrice(),
                                                i.getProduct().getOriginalPrice(),
                                                i.getProduct().getImage(),
                                                i.getProduct().getRating(),
                                                i.getProduct().getReviews(),
                                                i.getProduct().getBadge(),
                                                i.getProduct().getDescription(),
                                                i.getProduct().getDate(),
                                                null
                                        ),
                                        i.getQuantity()
                                ))
                                .collect(Collectors.toList())
                        : null,
                cart.getItems() != null ? cart.getItems().stream()
                        .mapToDouble(i -> i.getQuantity() * i.getProduct().getPrice()).sum()
                        : 0
        );
    }
}