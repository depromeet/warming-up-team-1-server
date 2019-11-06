package com.depromeet.warmup1.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private String image;
    private Integer money;
}
