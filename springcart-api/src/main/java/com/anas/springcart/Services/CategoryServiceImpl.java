package com.anas.springcart.Services;

import com.anas.springcart.DTO.CategoryDto;
import com.anas.springcart.DTO.ProductDto;
import com.anas.springcart.Models.CategoryModel;
import com.anas.springcart.Repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        return categoryRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        // Check if category already exists
        categoryRepository.findByName(categoryDto.getName())
                .ifPresent(c -> { throw new RuntimeException("Category already exists"); });

        CategoryModel category = new CategoryModel();
        category.setName(categoryDto.getName());
        CategoryModel saved = categoryRepository.save(category);

        return mapToDto(saved);
    }

    // --- Mapping ---
    private CategoryDto mapToDto(CategoryModel category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getProducts() != null ?
                        category.getProducts().stream()
                                .map(p -> new ProductDto(
                                        p.getId(),
                                        p.getName(),
                                        p.getBrand(),
                                        p.getCategory() != null ? p.getCategory().getName() : null,
                                        p.getPrice(),
                                        p.getOriginalPrice(),
                                        p.getImage(),
                                        p.getRating(),
                                        p.getReviews(),
                                        p.getBadge(),
                                        p.getDescription(),
                                        p.getDate(),
                                        null
                                ))
                                .collect(Collectors.toList())
                        : null
        );
    }
}