package com.anas.springcart.Models;
import lombok.*;
import jakarta.persistence.*;



@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;


}