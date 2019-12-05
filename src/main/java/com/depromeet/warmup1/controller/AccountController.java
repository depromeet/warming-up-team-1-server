package com.depromeet.warmup1.controller;


import com.depromeet.warmup1.dto.AccountRequest;
import com.depromeet.warmup1.dto.AccountResponse;
import com.depromeet.warmup1.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponse> saveAccount(@ModelAttribute AccountRequest request,
                                                       @RequestParam String connectId) {
        AccountResponse account = accountService.createAccount(request, connectId);
        return ResponseEntity.status(201).body(account);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId) {

        AccountResponse response = accountService.getAccount(accountId);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<String> updateAccount(@PathVariable Long accountId,
                                                @ModelAttribute AccountRequest request) {
        accountService.updateAccount(request, accountId);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
