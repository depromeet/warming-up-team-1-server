package com.depromeet.warmup1;

import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Transaction;
import com.depromeet.warmup1.entity.TransactionCategory;

public final class TestHelper {
    private TestHelper() {
    }

    public static AccountDto createAccountDto(Integer budget, Integer month) {
        AccountDto accountDto = new AccountDto();
        accountDto.setBudget(budget);
        accountDto.setMonth(month);
        return accountDto;
    }

    public static Account createAccount(Long id, Integer budget, Integer month) {
        Account account = createAccountDto(budget, month).toEntity();
        account.setId(id);
        return account;
    }

    public static Transaction createTransaction(Long id,
                                                Integer money,
                                                String category,
                                                TransactionCategory transactionCategory,
                                                String memo,
                                                Account account) {

        return Transaction.builder()
                .money(money)
                .category(category)
                .transactionCategory(transactionCategory)
                .memo(memo)
                .account(account)
                .build();


    }
}
