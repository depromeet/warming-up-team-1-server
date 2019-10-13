package com.depromeet.warmup1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private Integer budget;

    private Integer month;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactionList;

    @Builder
    private Account(Integer budget, Integer month){
        this.budget = budget;
        this.month = month;
    }

}
