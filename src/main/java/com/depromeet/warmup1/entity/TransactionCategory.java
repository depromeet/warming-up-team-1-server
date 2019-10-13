package com.depromeet.warmup1.entity;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransactionCategory {


    INCOME("INCOME"),
    EXPENDITURE("EXPENDITURE");

    private final String name;

}
