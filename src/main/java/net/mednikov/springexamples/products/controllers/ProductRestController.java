package net.mednikov.springexamples.products.controllers;

import net.mednikov.springexamples.products.domain.ProductDto;
import net.mednikov.springexamples.products.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ProductDto createProduct (@RequestBody ProductDto body){
        return this.productService.createProduct(body);
    }

    @PutMapping("/update")
    public @ResponseBody ProductDto updateProduct (@RequestBody ProductDto body){
        return this.productService.updateProduct(body);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct (@PathVariable Long id){
        this.productService.deleteProduct(id);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> getProductById (@PathVariable Long id){
        Optional<ProductDto> result = this.productService.getProductById(id);
        return ResponseEntity.of(result);
    }

    @GetMapping("/products")
    public @ResponseBody List<ProductDto> getProducts(@RequestParam(required = false, name="category") Long categoryId){
        if (categoryId != null){
            return this.productService.getProductsByCategory(categoryId);
        } else {
            return this.productService.getAllProducts();
        }
    }

}
