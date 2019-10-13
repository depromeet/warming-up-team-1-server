package com.depromeet.warmup1.dto;

import com.depromeet.warmup1.entity.Account;
import lombok.Getter;

@Getter
public class AccountDto {

    private Integer budget;

    private Integer month;


    public Account toEntity(){
        return Account.builder()
                .budget(budget)
                .month(month)
                .build();
    }

}
