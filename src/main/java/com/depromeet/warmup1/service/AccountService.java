package com.depromeet.warmup1.service;

import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.entity.Account;

public interface AccountService {
    Account createAccount(AccountDto accountDto);

    Account getAccount(Long accountId);

    void updateAccount(AccountDto accountDto, Long accountId);

    void deleteAccount(Long accountId);
}