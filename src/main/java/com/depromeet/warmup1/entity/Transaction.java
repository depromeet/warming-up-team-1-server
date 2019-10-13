package com.depromeet.warmup1.entity;

import com.depromeet.warmup1.dto.TransactionDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private Integer money;

    private String category;

    @Enumerated(value = EnumType.STRING)
    private TransactionCategory transactionCategory;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    @Builder
    private Transaction(
            Integer money,
            String category,
            TransactionCategory transactionCategory,
            Account account){
        this.money = money;
        this.category = category;
        this.transactionCategory = transactionCategory;
        this.account = account;
    }

}
