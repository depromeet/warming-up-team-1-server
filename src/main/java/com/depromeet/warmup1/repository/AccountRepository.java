package com.depromeet.warmup1.repository;

import com.depromeet.warmup1.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository  extends JpaRepository<Account, Long> {

}
