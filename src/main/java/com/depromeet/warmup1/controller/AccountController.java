package com.depromeet.warmup1.controller;


import com.depromeet.warmup1.dto.AccountRequest;
import com.depromeet.warmup1.dto.AccountResponse;
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
    public ResponseEntity<AccountResponse> saveAccount(@ModelAttribute AccountRequest request,
                                                       @RequestParam String connectId) {
        AccountResponse account = accountService.createAccount(request, connectId);
        return ResponseEntity.ok().body(account);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long id) {

        AccountResponse response = accountService.getAccount(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Long id,
                                                @ModelAttribute AccountRequest request) {
        accountService.updateAccount(request, id);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().body("success");
    }

}
