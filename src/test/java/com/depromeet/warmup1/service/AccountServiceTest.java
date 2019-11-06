package com.depromeet.warmup1.service;

import com.depromeet.warmup1.TestHelper;
import com.depromeet.warmup1.dto.AccountResponse;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.repository.AccountRepository;
import com.depromeet.warmup1.repository.ConnectRepository;
import com.depromeet.warmup1.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ConnectRepository connectRepository;


    private AccountService accountService;


    @Test
    public void 가계부_조회() {
        //given
        Account account = TestHelper.createAccount(1l, 50000, 12);
        when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(account));

        //when
        AccountResponse result = accountService.getAccount(account.getId());

        //then
        assertThat(result.getId(), is(1L));
        assertThat(result.getBudget(), is(50000));
        assertThat(result.getMonth(), is(12));

    }

    @Before
    public void setup() {
        accountService = new AccountServiceImpl(accountRepository, connectRepository);
    }
}
