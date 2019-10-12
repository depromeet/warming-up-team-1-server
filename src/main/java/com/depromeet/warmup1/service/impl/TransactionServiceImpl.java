package com.depromeet.warmup1.service.impl;

import com.depromeet.warmup1.dto.TransactionDto;
import com.depromeet.warmup1.entity.Transaction;
import com.depromeet.warmup1.repository.AccountRepository;
import com.depromeet.warmup1.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class TransactionServiceImpl {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void createTransaction(TransactionDto transactionDto){

    }

    @Transactional
    public void createAccount(){

    }


}
