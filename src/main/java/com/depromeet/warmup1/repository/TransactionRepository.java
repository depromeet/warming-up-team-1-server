package com.depromeet.warmup1.repository;

import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository  extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCategoryAndAccount(String category, Account account);
    List<Transaction> findAllByAccount(Account account);
}
