package com.depromeet.warmup1.repository;

import com.depromeet.warmup1.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

}
