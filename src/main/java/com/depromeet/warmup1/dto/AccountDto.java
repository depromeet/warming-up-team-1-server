package com.depromeet.warmup1.dto;

import com.depromeet.warmup1.entity.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class AccountDto {

    public static Response responseOf(Account account, Integer balance) {
        return Response.builder()
                .id(account.getId())
                .month(account.getMonth())
                .budget(account.getBudget())
                .balance(balance)
                .build();
    }

    @Getter
    @Setter
    public class Request {
        private Integer budget;

        private Integer month;

        public Account toEntity() {
            return Account.builder()
                    .budget(budget)
                    .month(month)
                    .build();
        }

    }

    @Builder
    @Getter
    @Setter
    public class Response {
        private Long id;
        private Integer month;
        private Integer budget;
        private Integer balance;


    }


}
