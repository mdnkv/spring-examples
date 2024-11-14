package net.mednikov.springexamples.products.domain;

import net.mednikov.springexamples.categories.domain.CategoryDto;
import net.mednikov.springexamples.categories.domain.CategoryDtoMapper;
import net.mednikov.springexamples.products.models.Product;

import java.util.function.Function;

public final class ProductDtoMapper implements Function<Product, ProductDto> {

    private final static CategoryDtoMapper categoryMapper = new CategoryDtoMapper();

    @Override
    public ProductDto apply(Product product) {
        CategoryDto category = product.getCategory().map(categoryMapper).orElse(null);
        Long categoryId = (category != null) ? category.id() : null;
        return new ProductDto(
                product.getId(),
                categoryId,
                product.getSkuNumber(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                category
        );
    }

}
