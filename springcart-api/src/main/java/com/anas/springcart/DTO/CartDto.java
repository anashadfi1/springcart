package com.anas.springcart.DTO;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Integer id;
    private List<CartItemDto> items;
    private Double totalPrice;
}