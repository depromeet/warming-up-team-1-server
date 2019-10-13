package com.depromeet.warmup1.controller;


import com.depromeet.warmup1.dto.AccountDto;
import com.depromeet.warmup1.dto.TransactionDto;
import com.depromeet.warmup1.entity.Account;
import com.depromeet.warmup1.entity.Transaction;
import com.depromeet.warmup1.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/accounts/{id}/transactions")
    public ResponseEntity<Transaction> saveTransaction(@PathVariable Long id,
                                                  @ModelAttribute TransactionDto transactionDto){
        Transaction transaction = transactionService.createTransaction(transactionDto, id);
        return ResponseEntity.ok().body(transaction);
    }

    public ResponseEntity<Account> saveAccount(@ModelAttribute AccountDto accountDto){
        Account account = transactionService.createAccount(accountDto);
        return ResponseEntity.ok().body(account);
    }

}
