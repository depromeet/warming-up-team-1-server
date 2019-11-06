package com.depromeet.warmup1.service;

import com.depromeet.warmup1.dto.AccountRequest;
import com.depromeet.warmup1.dto.AccountResponse;

public interface AccountService {
    AccountResponse createAccount(AccountRequest request, String connectId);

    AccountResponse getAccount(Long accountId);

    void updateAccount(AccountRequest request, Long accountId);

    void deleteAccount(Long accountId);
}
