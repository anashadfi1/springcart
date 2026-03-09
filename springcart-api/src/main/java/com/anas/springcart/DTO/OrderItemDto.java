package com.anas.springcart.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Integer id;
    private ProductDto product;
    private Integer quantity;
    private Double price;
}