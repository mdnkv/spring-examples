package net.mednikov.springexamples.categories.controllers;

import net.mednikov.springexamples.categories.domain.CategoryDto;
import net.mednikov.springexamples.categories.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody CategoryDto createCategory (@RequestBody CategoryDto body){
        return this.categoryService.createCategory(body);
    }

    @PutMapping("/update")
    public @ResponseBody CategoryDto updateCategory (@RequestBody CategoryDto body){
        return this.categoryService.updateCategory(body);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory (@PathVariable Long id){
        this.categoryService.deleteCategory(id);
    }

    @GetMapping("/categories")
    public @ResponseBody List<CategoryDto> getAllCategories(){
        return this.categoryService.getAllCategories();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        Optional<CategoryDto> result = this.categoryService.getCategoryById(id);
        return ResponseEntity.of(result);
    }

}
