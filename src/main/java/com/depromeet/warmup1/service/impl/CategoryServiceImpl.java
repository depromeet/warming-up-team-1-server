package com.depromeet.warmup1.service.impl;


import com.depromeet.warmup1.dto.CategoryDto;
import com.depromeet.warmup1.entity.Category;
import com.depromeet.warmup1.exception.NotFoundException;
import com.depromeet.warmup1.repository.CategoryRepository;
import com.depromeet.warmup1.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void createCategory(CategoryDto categoryDto) {
        Category category = categoryDto.toEntity();
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void updateCategory(Long categoryId, CategoryDto categoryDto) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(NotFoundException::new);

        category.update(categoryDto.getName(), categoryDto.getImage());


    }

    @Transactional(readOnly = true)
    public void getCategoryList(/*커플 pk*/) {
        //TODO couple pk 받아서 리스트들 다 가져오기
    }


}
