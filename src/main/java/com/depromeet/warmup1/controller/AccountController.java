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
    public ResponseEntity<Account> saveAccount(@ModelAttribute AccountDto.Request Request) {
        Account account = accountService.createAccount(Request);
        return ResponseEntity.ok().body(account);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountDto.Response> getAccount(@PathVariable Long id) {

        AccountDto.Response response = accountService.getAccount(id);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Long id, @ModelAttribute AccountDto.Request Request) {
        accountService.updateAccount(Request, id);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().body("success");
    }

}
