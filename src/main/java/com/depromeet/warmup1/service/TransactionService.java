package com.depromeet.warmup1.service;


import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.dto.TransactionDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Transaction;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(TransactionDto transactionDto, Long accountId);
    Transaction updateTransaction(TransactionDto transactionDto, Long transactionId, Long accountId);
    List<Transaction> getTransactionsByAccount(Long accountId, Pageable pageable);
    List<Transaction> getTransactionsByCategory(String category, Long accountId, Pageable pageable);
    void deleteTransaction(Long transactionId);
}
