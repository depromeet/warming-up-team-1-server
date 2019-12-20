package com.depromeet.warmup1.dto;

import com.depromeet.warmup1.entity.Category;
import com.depromeet.warmup1.entity.Connect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    private String name;
    private String image;

    public Category toEntity(Connect connect) {
        return Category.builder()
                .name(name)
                .image(image)
                .connect(connect)
                .build();
    }
}
