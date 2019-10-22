package com.depromeet.warmup1.controller;

import com.depromeet.warmup1.dto.LoginDto;
import com.depromeet.warmup1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("api/member/login")
    public LoginDto login(@RequestBody String kakaoToken) {
        return memberService.getOrCreateMember(kakaoToken);
    }

}