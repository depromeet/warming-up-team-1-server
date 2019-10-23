package com.depromeet.warmup1.controller;

import com.depromeet.warmup1.dto.LoginDto;
import com.depromeet.warmup1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("api/member/login")
    public ResponseEntity<LoginDto> login(@RequestBody String kakaoToken) {
        return ResponseEntity.ok(memberService.getOrCreateMember(kakaoToken));
    }

    @GetMapping("api/member/token")
    public ResponseEntity<String> getToken(@RequestHeader String refreshToken) {
        return ResponseEntity.ok(memberService.getJwtToken(refreshToken));
    }
}