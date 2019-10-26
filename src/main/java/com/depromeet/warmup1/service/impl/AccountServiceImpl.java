package com.depromeet.warmup1.service.impl;


import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.TransactionCategory;
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
    public Account createAccount(AccountDto.Request request) {
        Account account = request.toEntity();
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto.Response getAccount(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        Integer insufficientCash = account.getBudget();


        Integer expenditure = account.getTransactions().stream()
                .map(transaction -> {
                    if (transaction.getTransactionCategory() == TransactionCategory.EXPENDITURE) {
                        return -transaction.getMoney();
                    }
                    return transaction.getMoney();

                }).mapToInt(money -> money)
                .sum();

        insufficientCash += expenditure;


        return AccountDto.responseOf(account, insufficientCash);
    }

    @Override
    @Transactional
    public void updateAccount(AccountDto.Request request, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(NotFoundException::new);

        account.update(request.getBudget(), request.getMonth());

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
