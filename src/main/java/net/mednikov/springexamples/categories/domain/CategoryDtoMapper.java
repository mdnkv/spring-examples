package net.mednikov.springexamples.categories.domain;

import net.mednikov.springexamples.categories.models.Category;

import java.util.function.Function;

public final class CategoryDtoMapper implements Function<Category, CategoryDto> {

    @Override
    public CategoryDto apply(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }
}
