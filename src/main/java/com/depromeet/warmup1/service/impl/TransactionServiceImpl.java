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

import java.util.List;

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
    public Transaction updateTransaction(TransactionDto transactionDto, Long transactionId, Long accountId){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(NotFoundException::new);
        transaction.update(transactionDto, account);

        return transactionRepository.save(transaction);

    }

    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByAccount(Long accountId){
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        return transactionRepository.findAllByAccount(account);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByCategory(String category, Long accountId){

        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        return transactionRepository.findAllByCategoryAndAccount(category,account);
    }

    @Transactional
    public void deleteTransaction(Long transactionId){
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(NotFoundException::new);

        transactionRepository.delete(transaction);
    }


}
