package com.depromeet.warmup1.dto;


import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Transaction;
import com.depromeet.warmup1.entity.TransactionCategory;
import lombok.Getter;


@Getter
public class TransactionDto {
    private Integer money;

    private String category;

    private TransactionCategory transactionCategory;


    public Transaction toEntity(Account account){
        return Transaction.builder()
                .money(money)
                .category(category)
                .transactionCategory(transactionCategory)
                .account(account)
                .build();
    }
}
