package com.example.product_service.dto;

import com.example.product_service.enums.ProductStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProductRequest {

    @NotNull(message = "product name is required")
    private String name;

    private String description;

    @NotNull(message = "price is required")
    @DecimalMin(value = "0.01", message = "price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "category id is required")
    @Positive(message = "category id must be greater than 0")
    private Long categoryId;

    @NotNull(message = "Status is required")
    private ProductStatus status;

    public ProductRequest(){

    }

    public ProductRequest(String name, String description, BigDecimal price, Long categoryId, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        categoryId = categoryId;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
