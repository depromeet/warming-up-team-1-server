package com.depromeet.warmup1.controller;


import com.depromeet.warmup1.dto.TransactionDto;
import com.depromeet.warmup1.entity.Transaction;
import com.depromeet.warmup1.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/accounts/{id}/transactions")
    public ResponseEntity<Transaction> saveTransaction(@PathVariable Long id,
                                                       @ModelAttribute TransactionDto transactionDto) {
        Transaction transaction = transactionService.createTransaction(transactionDto, id);
        return ResponseEntity.ok().body(transaction);
    }

    @PostMapping("/accounts/{accountId}/transactions/{transactionId}")
    public ResponseEntity<String> updateTransaction(@PathVariable Long accountId,
                                                    @PathVariable Long transactionId,
                                                    @ModelAttribute TransactionDto transactionDto) {

        transactionService.updateTransaction(transactionDto, transactionId, accountId);

        return ResponseEntity.ok().body("success");

    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByAccount(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "20") int size,
                                                                      @PathVariable Long accountId) {

        Pageable pageable = PageRequest.of(page, size);

        List<Transaction> transactions = transactionService.getTransactionsByAccount(accountId, pageable);

        return ResponseEntity.ok().body(transactions);

    }

    @GetMapping("/accounts/{accountId}/transactions/category")
    public ResponseEntity<List<Transaction>> getTransactionsByCategory(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "20") int size,
                                                                       @NotNull @RequestParam String category,
                                                                       @PathVariable Long accountId) {

        Pageable pageable = PageRequest.of(page, size);

        List<Transaction> transactions = transactionService.getTransactionsByCategory(category, accountId, pageable);

        return ResponseEntity.ok().body(transactions);

    }

    @GetMapping("/accounts/transactions/{transactionId}")
    public ResponseEntity<String> getTransactionsByCategory(@PathVariable Long transactionId) {

        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok().body("success");

    }


}
