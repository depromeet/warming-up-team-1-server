package com.depromeet.warmup1.service.impl;

import com.depromeet.warmup1.dto.TransactionDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Category;
import com.depromeet.warmup1.entity.Transaction;
import com.depromeet.warmup1.exception.NotFoundException;
import com.depromeet.warmup1.repository.AccountRepository;
import com.depromeet.warmup1.repository.CategoryRepository;
import com.depromeet.warmup1.repository.TransactionRepository;
import com.depromeet.warmup1.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Transaction createTransaction(TransactionDto transactionDto, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        return transactionRepository.save(transactionDto.toEntity(account));
    }

    @Override
    @Transactional
    public void updateTransaction(TransactionDto transactionDto, Long transactionId, Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(NotFoundException::new);

        Category category = categoryRepository.findByName(transactionDto.getCategory())
                .orElseThrow(NotFoundException::new);

        transaction.update(transactionDto, account, category);

        transactionRepository.save(transaction);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByAccount(Long accountId, Pageable pageable) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        return transactionRepository.findAllByAccount(account, pageable)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getTransactionsByCategory(String category, Long accountId, Pageable pageable) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        return transactionRepository.findAllByCategoryAndAccount(category, account, pageable)
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteTransaction(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(NotFoundException::new);

        transactionRepository.delete(transaction);
    }


}
