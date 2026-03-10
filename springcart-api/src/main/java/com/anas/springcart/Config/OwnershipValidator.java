package com.anas.springcart.Config;


import com.anas.springcart.Exceptions.ResourceNotFoundException;
import com.anas.springcart.Models.CartModel;
import com.anas.springcart.Models.OrderModel;
import com.anas.springcart.Models.ProductModel;
import com.anas.springcart.Repositories.CartRepository;
import com.anas.springcart.Repositories.OrderRepository;
import com.anas.springcart.Repositories.ProductRepository;
import com.anas.springcart.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnershipValidator {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final SecurityUtils securityUtils;

    public Integer getCurrentUserId() {
        String username = securityUtils.getCurrentUsername();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username))
                .getId();
    }

    public void validateUserOwnership(Integer requestedUserId) {
        if (securityUtils.isAdmin()) return;
        if (!getCurrentUserId().equals(requestedUserId)) {
            throw new AccessDeniedException("You do not have access to this resource");
        }
    }

    public void validateCartOwnership(Integer cartId) {
        if (securityUtils.isAdmin()) return;
        CartModel cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found: " + cartId));
        if (cart.getUser().getId() != getCurrentUserId())  {
            throw new AccessDeniedException("You do not have access to this cart");
        }
    }

    public void validateOrderOwnership(Integer orderId) {
        if (securityUtils.isAdmin()) return;
        OrderModel order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderId));
        if (order.getUser().getId() != getCurrentUserId()) {
            throw new AccessDeniedException("You do not have access to this order");
        }
    }

    public void validateProductOwnership(Integer productId) {
        if (securityUtils.isAdmin()) return;
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId));
        if (product.getSeller().getId() != getCurrentUserId()) {
            throw new AccessDeniedException("You do not have access to this product");
        }
    }
}
