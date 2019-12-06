package com.depromeet.warmup1.service.impl;


import com.depromeet.warmup1.dto.AccountRequest;
import com.depromeet.warmup1.dto.AccountResponse;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Category;
import com.depromeet.warmup1.entity.Connect;
import com.depromeet.warmup1.entity.TransactionCategory;
import com.depromeet.warmup1.exception.NotFoundException;
import com.depromeet.warmup1.repository.AccountRepository;
import com.depromeet.warmup1.repository.CategoryRepository;
import com.depromeet.warmup1.repository.ConnectRepository;
import com.depromeet.warmup1.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final String[] DEFUALT_CATEGORY = {"육아", "교통비", "식비", "경조사", "문화생활", "공과금", "쇼핑", "기타"};
    private final AccountRepository accountRepository;
    private final ConnectRepository connectRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public AccountResponse createAccount(AccountRequest request, String connectKey) {
        Connect connect = connectRepository.findById(connectKey)
                .orElseThrow(NotFoundException::new);
        Optional<Account> findAccount = accountRepository.findByMonthAndConnect(request.getMonth(), connect);
        if (findAccount.isPresent()) {
            return getAccount(findAccount.get().getId());
        }
        Account account = request.toEntity(connect);

        IntStream.range(0, DEFUALT_CATEGORY.length)
                .forEach(index -> {
                    Category category = Category.builder()
                            .connect(connect)
                            .image(String.valueOf(index))
                            .name(DEFUALT_CATEGORY[index])
                            .build();
                    categoryRepository.save(category);
                });

        return AccountResponse.of(accountRepository.save(account), 0);
    }


    @Override
    @Transactional(readOnly = true)
    public AccountResponse getAccount(Long accountId) {
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


        return AccountResponse.of(account, insufficientCash);
    }


    @Override
    @Transactional
    public void updateAccount(AccountRequest request, Long accountId) {
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
