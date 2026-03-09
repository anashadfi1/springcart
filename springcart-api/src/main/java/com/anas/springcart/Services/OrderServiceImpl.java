package com.anas.springcart.Services;

import com.anas.springcart.DTO.OrderDto;
import com.anas.springcart.DTO.OrderItemDto;
import com.anas.springcart.DTO.ProductDto;
import com.anas.springcart.Models.CartModel;
import com.anas.springcart.Models.OrderItem;
import com.anas.springcart.Models.OrderModel;
import com.anas.springcart.Repositories.CartItemRepository;
import com.anas.springcart.Repositories.CartRepository;
import com.anas.springcart.Repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public OrderDto createOrder(Integer userId) {
        // Get user's cart (placeholder userId)
        CartModel cart = cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getItems() == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Create new order
        OrderModel order = new OrderModel();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setTotalPrice(cart.getItems().stream()
                .mapToDouble(i -> i.getQuantity() * i.getProduct().getPrice()).sum());

        // Map cart items to order items
        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice());
            return item;
        }).collect(Collectors.toList());

        order.setItems(orderItems);

        // Save order
        OrderModel savedOrder = orderRepository.save(order);

        // Clear the cart
        cart.getItems().clear();
        cartRepository.save(cart);

        return mapToDto(savedOrder);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Integer userId) {
        // Temporary: fetch all orders until UserModel is added
        return orderRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // --- Mapping Method ---
    private OrderDto mapToDto(OrderModel order) {
        List<OrderItemDto> items = order.getItems().stream()
                .map(i -> new OrderItemDto(
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
                        i.getQuantity(),
                        i.getPrice()
                ))
                .collect(Collectors.toList());

        return new OrderDto(
                order.getId(),
                order.getOrderDate(),
                order.getTotalPrice(),
                order.getStatus(),
                items
        );
    }
}