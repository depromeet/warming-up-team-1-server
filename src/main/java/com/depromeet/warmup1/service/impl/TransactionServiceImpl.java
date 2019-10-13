package com.depromeet.warmup1.service.impl;

import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.dto.TransactionDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Transaction;
import com.depromeet.warmup1.exception.NotFoundException;
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
    public Transaction createTransaction(TransactionDto transactionDto, Long accountId){
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        return transactionRepository.save(transactionDto.toEntity(account));
    }

    @Transactional
    public Account createAccount(AccountDto accountDto){
        Account account = accountDto.toEntity();
        return accountRepository.save(account);
    }


}
