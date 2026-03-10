package com.anas.springcart.DTO;

import java.time.LocalDateTime;

import com.anas.springcart.Models.enums.CategoryEnum;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer id;
    private String name;
    private String brand;
    private CategoryEnum category;
    private Double price;
    private Double originalPrice;
    private String image;
    private Double rating;
    private Integer reviews;
    private String badge;
    private String description;
    private LocalDateTime date;
    private List<ProductSpecificationDto> specifications;


    

}