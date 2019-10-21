package com.depromeet.warmup1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping("api/member/connectkey/{mid}")
	public ResponseEntity<String> createConnectKey(@PathVariable Long mid) {
		String key = memberService.createConnectKey(mid);
		if (key == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(key);
	}
	
	@GetMapping("api/member/couple/{key}")
	public ResponseEntity<List<Member>> getCouple(@PathVariable String key) {
		List<Member> couple = memberService.getCouple(key);
		if (couple == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(couple);
	}
	
	@PutMapping("api/member/connect/{mid}/{key}")
	public ResponseEntity<Member> connectMember(@PathVariable Long mid, @PathVariable String key) {
		Member member = memberService.connectMember(mid, key);
		if (member == null)
			return ResponseEntity.badRequest().body(null);
		return ResponseEntity.ok().body(member);
	}
}