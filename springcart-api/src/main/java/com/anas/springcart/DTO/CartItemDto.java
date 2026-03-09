package com.anas.springcart.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private Integer id;
    private ProductDto product;
    private Integer quantity;
}