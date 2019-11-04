package com.depromeet.warmup1.repository;

import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Connect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByMonthAndConnect(Integer Month, Connect connect);
}
