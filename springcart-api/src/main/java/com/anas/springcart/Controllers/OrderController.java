package com.anas.springcart.Controllers;

import com.anas.springcart.Config.OwnershipValidator;
import com.anas.springcart.DTO.OrderDto;
import com.anas.springcart.Services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('BUYER', 'ADMIN')")
public class OrderController {

    private final OrderService orderService;
    private final OwnershipValidator ownershipValidator;

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Integer userId) {
        ownershipValidator.validateUserOwnership(userId); // ✅ can only checkout as yourself
        return new ResponseEntity<>(orderService.createOrder(userId), HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersByUser(@PathVariable Integer userId) {
        ownershipValidator.validateUserOwnership(userId); // ✅ only own orders
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer orderId) {
        ownershipValidator.validateOrderOwnership(orderId); // ✅ only own order
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}