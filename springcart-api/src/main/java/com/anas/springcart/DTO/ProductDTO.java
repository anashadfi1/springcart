package com.anas.springcart.DTO;

import java.time.LocalDateTime;
import java.util.Map;

public class ProductDto {

    private Integer id;
    private String name;
    private String brand;
    private String category;

    private Double price;
    private Double originalPrice;

    private String image;

    private Double rating;
    private Integer reviews;

    private String badge;

    private String description;

    private LocalDateTime date;

    private Map<String, String> specs;

    public ProductDto() {}

    public ProductDto(Integer id, String name, String brand, String category,
                      Double price, Double originalPrice, String image,
                      Double rating, Integer reviews, String badge,
                      String description, LocalDateTime date,
                      Map<String, String> specs) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.originalPrice = originalPrice;
        this.image = image;
        this.rating = rating;
        this.reviews = reviews;
        this.badge = badge;
        this.description = description;
        this.date = date;
        this.specs = specs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getReviews() {
        return reviews;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Map<String, String> getSpecs() {
        return specs;
    }

    public void setSpecs(Map<String, String> specs) {
        this.specs = specs;
    }
}
