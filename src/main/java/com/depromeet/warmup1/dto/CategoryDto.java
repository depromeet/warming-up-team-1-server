package com.depromeet.warmup1.dto;


import com.depromeet.warmup1.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private String name;
    private String image;

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .image(image)
                .build();
    }
}
