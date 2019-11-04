package com.depromeet.warmup1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
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

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new LinkedList<>();

    @ManyToOne
    @JoinColumn(name = "connect_id")
    private Connect connect;


    @Builder
    private Account(Integer budget, Integer month, Connect connect) {
        this.connect = connect;
        this.budget = budget;
        this.month = month;
    }

    public void update(Integer budget, Integer month) {
        this.budget = budget;
        this.month = month;
    }


}
