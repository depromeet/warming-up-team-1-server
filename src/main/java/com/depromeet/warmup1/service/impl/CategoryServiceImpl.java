package com.depromeet.warmup1.service.impl;


import com.depromeet.warmup1.dto.CategoryDto;
import com.depromeet.warmup1.entity.Category;
import com.depromeet.warmup1.exception.NotFoundException;
import com.depromeet.warmup1.repository.CategoryRepository;
import com.depromeet.warmup1.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryDto categoryDto) {
        Category category = categoryDto.toEntity();
        categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Long categoryId, CategoryDto categoryDto) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(NotFoundException::new);

        category.update(categoryDto.getName(), categoryDto.getImage());

    }


}
