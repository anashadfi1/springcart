package com.anas.springcart.Services;

import com.anas.springcart.DTO.ProductDto;
import com.anas.springcart.DTO.ProductSpecificationDto;
import com.anas.springcart.Models.CategoryModel;
import com.anas.springcart.Models.ProductModel;
import com.anas.springcart.Repositories.CategoryRepository;
import com.anas.springcart.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Integer id) {
        return productRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        ProductModel product = mapToEntity(productDto);
        ProductModel saved = productRepository.save(product);
        return mapToDto(saved);
    }

    @Override
    public ProductDto updateProduct(Integer id, ProductDto productDto) {
        ProductModel product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setOriginalPrice(productDto.getOriginalPrice());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());
        product.setBadge(productDto.getBadge());
        product.setRating(productDto.getRating());
        product.setReviews(productDto.getReviews());
        product.setDate(productDto.getDate());

        if(productDto.getCategory() != null){
            CategoryModel category = categoryRepository.findByName(productDto.getCategory())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        ProductModel updated = productRepository.save(product);
        return mapToDto(updated);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    // --- Mapping Methods ---

    private ProductDto mapToDto(ProductModel product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getCategory() != null ? product.getCategory().getName() : null,
                product.getPrice(),
                product.getOriginalPrice(),
                product.getImage(),
                product.getRating(),
                product.getReviews(),
                product.getBadge(),
                product.getDescription(),
                product.getDate(),
                product.getSpecifications() != null ?
                        product.getSpecifications().stream()
                                .map(s -> new ProductSpecificationDto(s.getSpecName(), s.getSpecValue()))
                                .collect(Collectors.toList())
                        : null
        );
    }

    private ProductModel mapToEntity(ProductDto dto) {
        ProductModel product = new ProductModel();
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setPrice(dto.getPrice());
        product.setOriginalPrice(dto.getOriginalPrice());
        product.setImage(dto.getImage());
        product.setDescription(dto.getDescription());
        product.setBadge(dto.getBadge());
        product.setRating(dto.getRating());
        product.setReviews(dto.getReviews());
        product.setDate(dto.getDate());
        // category mapping handled in update/create method
        return product;
    }
}