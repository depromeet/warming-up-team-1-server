package com.depromeet.warmup1.dto;

import com.depromeet.warmup1.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountResponse {
    private Long id;
    private Integer month;
    private Integer budget;
    private Integer balance;

    public static AccountResponse of(Account account, Integer balance) {
        return new AccountResponse(
                account.getId(),
                account.getMonth(),
                account.getBudget(),
                balance
        );
    }
}
