package com.depromeet.warmup1.controller;


import com.depromeet.warmup1.dto.CategoryRequest;
import com.depromeet.warmup1.dto.CategoryResponse;
import com.depromeet.warmup1.entity.Category;
import com.depromeet.warmup1.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/categorys")
    public ResponseEntity<Category> createCategory(@ModelAttribute CategoryRequest categoryDto,
                                                   @RequestParam String conenctId) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDto, conenctId));
    }

    @PutMapping("/categorys")
    public ResponseEntity<String> updateCategory(@RequestParam Long categoryId,
                                                 @ModelAttribute CategoryRequest categoryDto) {
        categoryService.updateCategory(categoryId, categoryDto);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/categorys")
    public ResponseEntity<List<CategoryResponse>> getCategory(@RequestAttribute String id, @RequestParam Long accountId) {
        return ResponseEntity.ok(categoryService.getCategoryList(id, accountId));

    }

}
