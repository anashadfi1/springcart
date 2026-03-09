package com.anas.springcart.Repositories;

import com.anas.springcart.Models.CategoryModel;
import com.anas.springcart.Models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel,Integer> {
    List<ProductModel> findByCategory(CategoryModel category);
    List<ProductModel> findByNameContainingIgnoreCase(String name);


}
