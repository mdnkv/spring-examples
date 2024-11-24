package net.mednikov.springexamples.products.services;

import net.mednikov.springexamples.categories.models.Category;
import net.mednikov.springexamples.categories.repositories.CategoryRepository;
import net.mednikov.springexamples.products.domain.ProductDto;
import net.mednikov.springexamples.products.domain.ProductDtoMapper;
import net.mednikov.springexamples.products.exceptions.ProductAlreadyExistsException;
import net.mednikov.springexamples.products.exceptions.ProductNotFoundException;
import net.mednikov.springexamples.products.models.Product;
import net.mednikov.springexamples.products.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDtoMapper mapper;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = new ProductDtoMapper();
    }

    @Override
    public ProductDto createProduct(ProductDto request) {
        String skuNumber = request.getSkuNumber();
        if (this.productRepository.findBySkuNumber(skuNumber).isPresent()){
            throw new ProductAlreadyExistsException();
        }
        Product product = new Product.ProductBuilder()
                .withSkuNumber(skuNumber)
                .withName(request.getName())
                .withDescription(request.getDescription())
                .withPrice(request.getPrice())
                .build();
        if (request.getCategoryId() != null) {
            Category category = this.categoryRepository.getReferenceById(request.getCategoryId());
            product.setCategory(category);
        }

        Product result = this.productRepository.save(product);
        return mapper.apply(result);
    }

    @Override
    public ProductDto updateProduct(ProductDto request) {
        Product product = this.productRepository.findById(request.getId()).orElseThrow(ProductNotFoundException::new);

        if (request.getCategoryId() != null) {
            Category category = this.categoryRepository.getReferenceById(request.getCategoryId());
            product.setCategory(category);
        }
        product.setSkuNumber(request.getSkuNumber());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        Product result = this.productRepository.save(product);
        return mapper.apply(result);
    }

    @Override
    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Optional<ProductDto> getProductById(Long id) {
        return this.productRepository.findById(id).map(mapper);
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return this.productRepository.findAllByCategoryId(categoryId).stream().map(mapper).toList();
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return this.productRepository.findAll().stream().map(mapper).toList();
    }

}
