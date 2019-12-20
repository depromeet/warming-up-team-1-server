package com.depromeet.warmup1.controller;


import com.depromeet.warmup1.dto.TransactionDto;
import com.depromeet.warmup1.dto.TransactionResponse;
import com.depromeet.warmup1.service.TransactionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @ApiOperation(value = "거래내역 추가")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "가계부 고유키", paramType = "path"),
            @ApiImplicitParam(name = "transactionDto", value = "거래 정보", paramType = "body")
    })
    @PostMapping("/accounts/{accountId}/transactions")
    public ResponseEntity saveTransaction(@PathVariable Long accountId,
                                          @RequestBody TransactionDto transactionDto) {
        transactionService.createTransaction(transactionDto, accountId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @ApiOperation(value = "거래내역 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "가계부 고유키", paramType = "path"),
            @ApiImplicitParam(name = "transactionId", value = "거래내역 고유키", paramType = "path"),
            @ApiImplicitParam(name = "transactionDto", value = "거래 정보", paramType = "body")
    })
    @PutMapping("/accounts/{accountId}/transactions/{transactionId}")
    public ResponseEntity updateTransaction(@PathVariable Long accountId,
                                            @PathVariable Long transactionId,
                                            @RequestBody TransactionDto transactionDto) {

        transactionService.updateTransaction(transactionDto, transactionId, accountId);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }


    @ApiOperation(value = "월별 거래내역")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "가계부 고유키", paramType = "path"),
            @ApiImplicitParam(name = "page", value = "거래내역 고유키", paramType = "param")
    })
    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByAccount(@RequestParam(defaultValue = "0") int page,
                                                                              @RequestParam(defaultValue = "20") int size,
                                                                              @PathVariable Long accountId) {

        Pageable pageable = PageRequest.of(page, size);

        List<TransactionResponse> transactions = transactionService.getTransactionsByAccount(accountId, pageable);

        return ResponseEntity.ok().body(transactions);

    }

    @ApiOperation(value = "월별 선택한 카테고리 거래내역")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "가계부 고유키", paramType = "path"),
            @ApiImplicitParam(name = "categoryId", value = "카테고리 고유키", paramType = "path"),
            @ApiImplicitParam(name = "page", value = "거래내역 고유키", paramType = "param")
    })
    @GetMapping("/accounts/{accountId}/categories/{categoryId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByCategory(@RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "20") int size,
                                                                               @NotNull @PathVariable Long categoryId,
                                                                               @PathVariable Long accountId) {

        Pageable pageable = PageRequest.of(page, size);

        List<TransactionResponse> transactions = transactionService.getTransactionsByCategory(categoryId, accountId, pageable);

        return ResponseEntity.ok().body(transactions);

    }


    @ApiOperation(value = "거래내역 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "transactionId", value = "거래내역 고유키", paramType = "path")
    })
    @DeleteMapping("/accounts/transactions/{transactionId}")
    public ResponseEntity getTransactionsByCategory(@PathVariable Long transactionId) {

        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
