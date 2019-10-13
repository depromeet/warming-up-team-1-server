package com.depromeet.warmup1.service;


import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.dto.TransactionDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Transaction;

public interface TransactionService {
    Transaction createTransaction(TransactionDto transactionDto, Long accountId);
    Account createAccount(AccountDto accountDto);
}
