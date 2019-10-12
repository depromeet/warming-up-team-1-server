package com.depromeet.warmup1.entity;

public enum TransactionCategory {


    INCOME("INCOME"),
    EXPENDITURE("EXPENDITURE");

    private final String name;

    TransactionCategory(String name) {
        this.name = name;
    }



}
