package com.anas.springcart.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="product_table")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String brand;

    private Double price;
    private Double originalPrice;

    private String image;

    private Double rating;
    private Integer reviews;

    private String badge;

    @Column(length = 2000)
    private String description;

    private LocalDateTime date;
    @ElementCollection
    @CollectionTable(name = "product_specs", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "spec_key")
    @Column(name = "spec_value")
    private Map<String, String> specs;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductSpecification> specifications;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private CategoryModel category;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserModel seller;
}
