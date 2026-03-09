package com.anas.springcart.Repositories;

import com.anas.springcart.Models.ProductModel;
import com.anas.springcart.Models.ProductSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSpecificationRepository extends JpaRepository<ProductSpecification, Integer> {
    List<ProductSpecification> findByProduct(ProductModel product);
}