package com.depromeet.warmup1.dto;


import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.TransactionCategory;
import lombok.Getter;


@Getter
public class TransactionDto {
    private Integer money;

    private String category;

    private TransactionCategory transactionCategory;

    private Long account;
}
