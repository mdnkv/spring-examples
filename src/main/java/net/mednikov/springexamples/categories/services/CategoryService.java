package net.mednikov.springexamples.categories.services;

import net.mednikov.springexamples.categories.domain.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryDto createCategory (CategoryDto categoryDto);

    CategoryDto updateCategory (CategoryDto categoryDto);

    Optional<CategoryDto> getCategoryById (Long id);

    List<CategoryDto> getAllCategories();

    void deleteCategory (Long id);

}
