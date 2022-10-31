package ru.practicum.explore_with_me.category.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.explore_with_me.category.dto.CategoryDto;
import ru.practicum.explore_with_me.category.dto.NewCategoryDto;
import ru.practicum.explore_with_me.category.model.Category;

@Component
public class CategoryMapper {
    public Category toCategoryFromNewCategory(NewCategoryDto categoryDto) {
        return new Category(categoryDto.getName());
    }

    public CategoryDto toCategoryDto(Category category) {
        return new CategoryDto(category.getId(), category.getName());
    }

    public Category toCategoryFromCategoryDto(CategoryDto categoryDto) {
        return new Category(categoryDto.getId(), categoryDto.getName());
    }

}
