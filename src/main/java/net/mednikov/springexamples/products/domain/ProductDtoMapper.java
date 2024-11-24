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
        Long categoryId = (category != null) ? category.getId() : null;
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(categoryId);
        productDto.setCategory(category);
        productDto.setSkuNumber(product.getSkuNumber());
        return productDto;
    }

}
