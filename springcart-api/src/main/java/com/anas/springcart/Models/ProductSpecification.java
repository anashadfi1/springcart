package com.anas.springcart.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product_specifications")
public class ProductSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String specName;

    private String specValue;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private ProductModel product;
}
