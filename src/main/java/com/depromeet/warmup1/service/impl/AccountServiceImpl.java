package com.depromeet.warmup1.service.impl;


import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.exception.NotFoundException;
import com.depromeet.warmup1.repository.AccountRepository;
import com.depromeet.warmup1.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public Account createAccount(AccountDto accountDto) {
        Account account = accountDto.toEntity();
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        return account;
    }

    @Override
    @Transactional
    public void updateAccount(AccountDto accountDto, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        account.update(accountDto.getBudget(), accountDto.getMonth());

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        accountRepository.delete(account);
    }


}
