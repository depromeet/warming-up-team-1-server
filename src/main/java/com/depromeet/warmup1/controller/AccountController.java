package com.depromeet.warmup1.controller;


import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id){

        Account account = accountService.getAccount(id);

        return ResponseEntity.ok().body(account);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Long id, @ModelAttribute AccountDto accountDto){
        accountService.updateAccount(accountDto,id);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().body("success");
    }

}
