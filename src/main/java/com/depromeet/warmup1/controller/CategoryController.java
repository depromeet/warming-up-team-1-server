package com.depromeet.warmup1.controller;


import com.depromeet.warmup1.dto.CategoryRequest;
import com.depromeet.warmup1.dto.CategoryResponse;
import com.depromeet.warmup1.entity.Category;
import com.depromeet.warmup1.service.CategoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "카테고리 생성")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryDto", value = "카테고리 데이터", paramType = "body"),
    })
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@ModelAttribute CategoryRequest categoryDto,
                                                   @RequestParam String connectKey) {
        return ResponseEntity.ok(categoryService.createCategory(categoryDto, connectKey));
    }

    @ApiOperation(value = "카테고리 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryDto", value = "카테고리 데이터", paramType = "body"),
    })
    @PutMapping("/categories")
    public ResponseEntity<String> updateCategory(@RequestParam Long categoryId,
                                                 @ModelAttribute CategoryRequest categoryDto) {
        categoryService.updateCategory(categoryId, categoryDto);
        return ResponseEntity.ok("success");
    }

    @ApiOperation(value = "카테고리 리스트 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryDto", value = "카테고리 데이터", paramType = "body"),
    })
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getCategory(@RequestAttribute String userId,
                                                              @RequestParam Long accountId) {
        return ResponseEntity.ok(categoryService.getCategoryList(userId, accountId));

    }

}
