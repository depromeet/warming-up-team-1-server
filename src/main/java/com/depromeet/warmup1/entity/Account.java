package com.depromeet.warmup1.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private Integer budget;

    private Integer month;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactionList;

}