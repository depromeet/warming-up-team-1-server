package com.depromeet.warmup1.service;

import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.entity.Account;

public interface AccountService {
    Account createAccount(AccountDto.Request request);

    AccountDto.Response getAccount(Long accountId);

    void updateAccount(AccountDto.Request request, Long accountId);

    void deleteAccount(Long accountId);
}
