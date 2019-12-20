package com.depromeet.warmup1.service;


import com.depromeet.warmup1.dto.TransactionDto;
import com.depromeet.warmup1.dto.TransactionResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    void createTransaction(TransactionDto transactionDto, Long accountId);

    void updateTransaction(TransactionDto transactionDto, Long transactionId, Long accountId);

    List<TransactionResponse> getTransactionsByAccount(Long accountId, Pageable pageable);

    List<TransactionResponse> getTransactionsByCategory(Long categoryId, Long accountId, Pageable pageable);

    void deleteTransaction(Long transactionId);
}
