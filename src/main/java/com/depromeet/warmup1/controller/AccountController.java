package com.depromeet.warmup1.controller;


import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<Account> saveAccount(@ModelAttribute AccountDto accountDto){
        Account account = accountService.createAccount(accountDto);
        return ResponseEntity.ok().body(account);
    }

}
