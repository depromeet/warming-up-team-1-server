package com.depromeet.warmup1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private Integer money;

    private String category;

    @Enumerated(value = EnumType.STRING)
    private TransactionCategory transactionCategory;
}
