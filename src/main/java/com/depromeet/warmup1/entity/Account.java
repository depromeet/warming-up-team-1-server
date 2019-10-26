package com.depromeet.warmup1.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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


    @Builder
    private Account(Integer budget, Integer month) {
        this.budget = budget;
        this.month = month;
    }

    public void update(Integer budget, Integer month) {
        this.budget = budget;
        this.month = month;
    }


}
