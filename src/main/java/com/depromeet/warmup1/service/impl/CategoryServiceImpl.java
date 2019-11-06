package com.depromeet.warmup1.service.impl;


import com.depromeet.warmup1.dto.CategoryRequest;
import com.depromeet.warmup1.dto.CategoryResponse;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Category;
import com.depromeet.warmup1.entity.Connect;
import com.depromeet.warmup1.exception.NotFoundException;
import com.depromeet.warmup1.repository.AccountRepository;
import com.depromeet.warmup1.repository.CategoryRepository;
import com.depromeet.warmup1.repository.ConnectRepository;
import com.depromeet.warmup1.repository.TransactionRepository;
import com.depromeet.warmup1.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ConnectRepository connectRepository;
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Category createCategory(CategoryRequest categoryDto, String connectKey) {
        Connect connect = connectRepository.findById(connectKey)
                .orElseThrow(NotFoundException::new);
        Category category = categoryDto.toEntity(connect);
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void updateCategory(Long categoryId, CategoryRequest categoryDto) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(NotFoundException::new);

        category.update(categoryDto.getName(), categoryDto.getImage());


    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponse> getCategoryList(String connectKey, Long accountId) {

        Connect connect = connectRepository.findById(connectKey)
                .orElseThrow(NotFoundException::new);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);
        List<Category> categories = categoryRepository.findAllByConnect(connect);


        return categories.stream()
                .map(category -> {
                    Integer m = transactionRepository.findAllByCategoryAndAccount(category, account)
                            .stream()
                            .map(transaction -> transaction.getMoney())
                            .mapToInt(money -> money)
                            .sum();

                    return CategoryResponse.builder()
                            .id(category.getId())
                            .image(category.getImage())
                            .money(m)
                            .name(category.getName())
                            .build();

                }).collect(Collectors.toList());
    }


}
