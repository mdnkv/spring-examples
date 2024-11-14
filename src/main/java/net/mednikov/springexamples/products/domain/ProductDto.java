package net.mednikov.springexamples.products.domain;

import net.mednikov.springexamples.categories.domain.CategoryDto;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        Long categoryId,
        String skuNumber,
        String name,
        String description,
        BigDecimal price,
        CategoryDto category
) {
}
