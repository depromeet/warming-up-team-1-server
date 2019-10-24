package com.depromeet.warmup1.service;


import com.depromeet.warmup1.TestHelper;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Transaction;
import com.depromeet.warmup1.entity.TransactionCategory;
import com.depromeet.warmup1.repository.AccountRepository;
import com.depromeet.warmup1.repository.TransactionRepository;
import com.depromeet.warmup1.service.impl.TransactionServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;

    private TransactionService transactionService;

    @Before
    public void setup() {
        transactionService = new TransactionServiceImpl(transactionRepository, accountRepository);
    }

    @Test
    public void 월별_거래내역_조회() {
        //given
        Account account = TestHelper.createAccount(1l, 50000, 12);

        List<Transaction> transactionList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            transactionList.add(TestHelper.createTransaction(
                    Long.valueOf(i),
                    i * 1000,
                    "test",
                    TransactionCategory.EXPENDITURE,
                    "memo",
                    account
            ));
        }

        Pageable pageable = PageRequest.of(0, 20);

        when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(account));
        when(transactionRepository.findAllByAccount(account, pageable))
                .thenReturn(new PageImpl(transactionList));


        //when
        List<Transaction> result = transactionService.getTransactionsByAccount(account.getId(), pageable);

        //then
        assertThat(result.size(), is(5));


    }
}
