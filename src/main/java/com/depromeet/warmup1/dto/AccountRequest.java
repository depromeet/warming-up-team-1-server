package com.depromeet.warmup1.dto;

import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Connect;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AccountRequest {

    private Integer budget;

    private Integer month;

    public Account toEntity(Connect connect) {
        return Account.builder()
                .budget(budget)
                .month(month)
                .connect(connect)
                .build();
    }

}
