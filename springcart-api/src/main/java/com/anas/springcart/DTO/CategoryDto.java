package com.anas.springcart.DTO;

import com.anas.springcart.Models.enums.CategoryEnum;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer id;
    private CategoryEnum name;
    private List<ProductDto> products;
}
