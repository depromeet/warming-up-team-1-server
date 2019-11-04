package com.depromeet.warmup1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.warmup1.dto.LoginDto;
import com.depromeet.warmup1.entity.Member;
import com.depromeet.warmup1.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;

	@PostMapping("api/members/login")
	public ResponseEntity<LoginDto> login(@RequestBody String kakaoToken) {
		return ResponseEntity.ok(memberService.getOrCreateMember(kakaoToken));
	}

	@GetMapping("api/members/token")
	public ResponseEntity<String> getToken(@RequestHeader String refreshToken) {
		return ResponseEntity.ok(memberService.getJwtToken(refreshToken));
	}

	@GetMapping("api/members/connect-key/{mid}")
	public ResponseEntity<String> createConnectKey(@PathVariable Long mid) {
		String key = memberService.createConnectKey(mid);
		
		return ResponseEntity.ok().body(key);
	}

	@GetMapping("api/members/couple/{key}")
	public ResponseEntity<List<Member>> getCouple(@PathVariable String key) {
		List<Member> couple = memberService.getCouple(key);
		
		return ResponseEntity.ok().body(couple);
	}

	@PutMapping("api/members/connect/{mid}/{key}")
	public ResponseEntity<Member> connectMember(@PathVariable Long mid, @PathVariable String key) {
		Member member = memberService.connectMember(mid, key);
		
		return ResponseEntity.ok().body(member);
	}
}