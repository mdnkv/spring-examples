package net.mednikov.springexamples.categories.services;

import net.mednikov.springexamples.categories.domain.CategoryDto;
import net.mednikov.springexamples.categories.domain.CategoryDtoMapper;
import net.mednikov.springexamples.categories.exceptions.CategoryAlreadyExistsException;
import net.mednikov.springexamples.categories.exceptions.CategoryNotFoundException;
import net.mednikov.springexamples.categories.models.Category;
import net.mednikov.springexamples.categories.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDtoMapper mapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.mapper =  new CategoryDtoMapper();
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        String categoryName = categoryDto.getName();
        if (this.categoryRepository.findByName(categoryName).isPresent()){
            throw new CategoryAlreadyExistsException();
        }
        Category category = new Category.CategoryBuilder().withName(categoryName).build();
        Category result = this.categoryRepository.save(category);
        return mapper.apply(result);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = this.categoryRepository
                .findById(categoryDto.getId())
                .orElseThrow(CategoryNotFoundException::new);

        category.setName(categoryDto.getName());

        Category result = this.categoryRepository.save(category);
        return mapper.apply(result);
    }

    @Override
    public Optional<CategoryDto> getCategoryById(Long id) {
        return this.categoryRepository.findById(id).map(mapper);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return this.categoryRepository.findAll().stream().map(mapper).toList();
    }

    @Override
    public void deleteCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

}
