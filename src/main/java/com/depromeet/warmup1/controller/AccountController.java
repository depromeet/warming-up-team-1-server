package com.depromeet.warmup1.controller;


import com.depromeet.warmup1.dto.AccountRequest;
import com.depromeet.warmup1.dto.AccountResponse;
import com.depromeet.warmup1.service.AccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ApiOperation(value = "가계부 생성(월마다)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountRequest", value = "월마다 가계부 기본 정보", paramType = "body")
    })
    @PostMapping("/accounts")
    public ResponseEntity<AccountResponse> saveAccount(@RequestBody AccountRequest accountRequest,
                                                       @RequestParam String connectKey) {
        AccountResponse account = accountService.createAccount(accountRequest, connectKey);
        return ResponseEntity.status(201).body(account);
    }


    @ApiOperation(value = "가계부 데이터 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "가계부 고유키", paramType = "path")
    })
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long accountId) {

        AccountResponse response = accountService.getAccount(accountId);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "가계부 데이터 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "가계부 고유키", paramType = "path"),
            @ApiImplicitParam(name = "accountRequest", value = "월마다 가계부 기본 정보", paramType = "body")
    })
    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<String> updateAccount(@PathVariable Long accountId,
                                                @RequestBody AccountRequest request) {
        accountService.updateAccount(request, accountId);
        return ResponseEntity.ok().body("success");
    }

    @ApiOperation(value = "가계부 데이터 삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "가계부 고유키", paramType = "path")
    })
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
