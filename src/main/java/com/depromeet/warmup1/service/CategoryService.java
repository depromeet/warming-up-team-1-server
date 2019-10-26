package com.depromeet.warmup1.service;

import com.depromeet.warmup1.dto.CategoryDto;

public interface CategoryService {
    void createCategory(CategoryDto categoryDto);

    void updateCategory(Long categoryId, CategoryDto categoryDto);
}
