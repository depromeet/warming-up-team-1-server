package com.depromeet.warmup1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.warmup1.dto.LoginDto;
import com.depromeet.warmup1.entity.Member;
import com.depromeet.warmup1.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@PostMapping("api/member/login")
	public Member login(@RequestBody LoginDto loginDto) {
		Member loginedMember = memberService.getOrCreateMember(loginDto.getKakaoToken());
		return loginedMember;
	}
	
}