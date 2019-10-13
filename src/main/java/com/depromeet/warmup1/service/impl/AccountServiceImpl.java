package com.depromeet.warmup1.service.impl;


import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl {
    private final AccountRepository accountRepository;

    @Transactional
    public Account createAccount(AccountDto accountDto){
        Account account = accountDto.toEntity();
        return accountRepository.save(account);
    }

}
