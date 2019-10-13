package com.depromeet.warmup1.repository;

import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
    Page<Transaction> findAllByCategoryAndAccount(String category, Account account, Pageable pageable);
    Page<Transaction> findAllByAccount(Account account, Pageable pageable);
}
