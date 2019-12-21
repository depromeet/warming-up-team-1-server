package com.depromeet.warmup1.dto;

import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Connect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class AccountRequest {

    private Integer budget;

    private Integer month;

    public Account toEntity(Connect connect) {
        log.info(String.valueOf(this.budget));
        return Account.builder()
                .budget(this.budget)
                .month(this.month)
                .connect(connect)
                .build();
    }

}
