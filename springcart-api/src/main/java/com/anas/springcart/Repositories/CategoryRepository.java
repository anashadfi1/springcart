package com.anas.springcart.Repositories;

import com.anas.springcart.Models.CategoryModel;
import com.anas.springcart.Models.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {
    Optional<CategoryModel> findByName(CategoryEnum name);
}