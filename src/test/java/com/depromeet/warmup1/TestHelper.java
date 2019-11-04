package com.depromeet.warmup1;

import com.depromeet.warmup1.dto.AccountRequest;
import com.depromeet.warmup1.dto.CategoryRequest;
import com.depromeet.warmup1.entity.*;

public final class TestHelper {
    private TestHelper() {
    }

    public static Connect createConnect() {
        Connect connect = new Connect();
        connect.setConnectKey("1");
        return connect;
    }

    public static AccountRequest createAccountDto(Integer budget, Integer month) {
        AccountRequest accountDto = new AccountRequest(budget, month);
        return accountDto;
    }

    public static Account createAccount(Long id, Integer budget, Integer month) {
        Account account = createAccountDto(budget, month).toEntity(createConnect());
        account.setId(id);
        return account;
    }

    public static Transaction createTransaction(Long id,
                                                Integer money,
                                                Category category,
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

    public static Category createCategory() {
        CategoryRequest categoryDto = new CategoryRequest("11", "1");
        Connect connect = new Connect();
        connect.setConnectKey("key");
        return categoryDto.toEntity(connect);
    }
}
