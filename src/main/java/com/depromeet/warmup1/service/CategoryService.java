package com.depromeet.warmup1.service;

import com.depromeet.warmup1.dto.CategoryRequest;
import com.depromeet.warmup1.dto.CategoryResponse;
import com.depromeet.warmup1.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CategoryRequest categoryDto, Long connectKey);

    void updateCategory(Long categoryId, CategoryRequest categoryDto);

    List<CategoryResponse> getCategoryList(Long connectKey, Long accountId);
}
