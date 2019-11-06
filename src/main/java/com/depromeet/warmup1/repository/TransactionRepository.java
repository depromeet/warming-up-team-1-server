package com.depromeet.warmup1.repository;

import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Category;
import com.depromeet.warmup1.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCategoryAndAccount(Category category, Account account);

    Page<Transaction> findAllByCategoryAndAccount(Category category, Account account, Pageable pageable);

    Page<Transaction> findAllByAccount(Account account, Pageable pageable);
}
