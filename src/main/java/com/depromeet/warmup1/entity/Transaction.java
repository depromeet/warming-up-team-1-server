package com.depromeet.warmup1.entity;

import com.depromeet.warmup1.dto.TransactionDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private Integer money;

    @Enumerated(value = EnumType.STRING)
    private TransactionCategory transactionCategory;

    private String memo;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    private Transaction(
            Integer money,
            Category category,
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
                       Account account,
                       Category category) {
        this.money = transactionDto.getMoney();
        this.category = category;
        this.transactionCategory = transactionDto.getTransactionCategory();
        this.memo = transactionDto.getMemo();
        this.account = account;
    }


}
