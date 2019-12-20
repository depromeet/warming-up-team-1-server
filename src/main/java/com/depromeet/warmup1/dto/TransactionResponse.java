package com.depromeet.warmup1.dto;


import com.depromeet.warmup1.entity.TransactionCategory;
import lombok.*;

@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionResponse {

    private Long id;

    private Integer money;

    private Long categoryId;

    private TransactionCategory transactionCategory;

    private String memo;

}
