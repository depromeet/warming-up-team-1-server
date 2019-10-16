package com.depromeet.warmup1.entity;

import com.depromeet.warmup1.dto.TransactionDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String memo;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private LocalDateTime createdAt;


    @Builder
    private Transaction(
            Integer money,
            String category,
            TransactionCategory transactionCategory,
            String memo,
            Account account) {
        this.money = money;
        this.category = category;
        this.transactionCategory = transactionCategory;
        this.memo = memo;
        this.account = account;
    }

    public void update(TransactionDto transactionDto,
                       Account account) {
        this.money = transactionDto.getMoney();
        this.category = transactionDto.getCategory();
        this.transactionCategory = transactionDto.getTransactionCategory();
        this.memo = transactionDto.getMemo();
        this.account = account;
    }


}
