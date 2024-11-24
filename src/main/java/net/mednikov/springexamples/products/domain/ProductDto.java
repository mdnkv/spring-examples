package net.mednikov.springexamples.products.domain;

import net.mednikov.springexamples.categories.domain.CategoryDto;

import java.math.BigDecimal;

public class ProductDto {
    private Long id;
    private Long categoryId;
    private String skuNumber;
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryDto category;

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getId() {
        return id;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
