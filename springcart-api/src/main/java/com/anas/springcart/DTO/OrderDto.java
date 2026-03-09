package com.anas.springcart.DTO;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Integer id;
    private LocalDateTime orderDate;
    private Double totalPrice;
    private String status;
    private List<OrderItemDto> items;
}
