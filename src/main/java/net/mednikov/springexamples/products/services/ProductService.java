package net.mednikov.springexamples.products.services;

import net.mednikov.springexamples.products.domain.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDto createProduct (ProductDto product);

    ProductDto updateProduct (ProductDto product);

    void deleteProduct (Long id);

    Optional<ProductDto> getProductById (Long id);

    List<ProductDto> getProductsByCategory (Long categoryId);

    List<ProductDto> getAllProducts ();

}
