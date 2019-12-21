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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인/회원가입")
	@PostMapping("api/members/login")
	@ApiImplicitParam(name = "kakaoToken", value = "카카오토큰", required = true)
	public ResponseEntity<LoginDto> login(@RequestBody String kakaoToken) {
		return ResponseEntity.ok(memberService.getOrCreateMember(kakaoToken));
	}

	@ApiOperation(value = "refreshToken을 통한 JWT Token재발급")
	@GetMapping("api/members/token")
	public ResponseEntity<String> getToken(@RequestHeader String refreshToken) {
		return ResponseEntity.ok(memberService.getJwtToken(refreshToken));
	}

	@ApiOperation(value = "연결키 생성")
	@GetMapping("api/members/connect-key/{mid}")
	public ResponseEntity<String> createConnectKey(@PathVariable Long mid) {
		String key = memberService.createConnectKey(mid);

		return ResponseEntity.ok().body(key);
	}

	@ApiOperation(value = "connectKey를 통한 연동된 부부 조회")
	@ApiImplicitParam(name = "connectkey", value = "연동키", required = true)
	@GetMapping("api/members/couple/{connectkey}")
	public ResponseEntity<List<Member>> getCouple(@PathVariable String connectkey) {
		List<Member> couple = memberService.getCouple(connectkey);

		return ResponseEntity.ok().body(couple);
	}

	@ApiOperation(value = "connectKey를 이용한 부부 연동")
	@ApiImplicitParams({ @ApiImplicitParam(name = "mid", value = "멤버id", required = true),
			@ApiImplicitParam(name = "connectkey", value = "연동키", required = true) })
	@PutMapping("api/members/connect/{mid}/{connectkey}")
	public ResponseEntity<Member> connectMember(@PathVariable Long mid, @PathVariable String connectkey) {
		Member member = memberService.connectMember(mid, connectkey);

		return ResponseEntity.ok().body(member);
	}

	@GetMapping("api/members")
	public ResponseEntity<Member> getMember(@PathVariable Long mid) {
		Member member = memberService.getMemberByMid(mid);
		return ResponseEntity.ok().body(member);
	}
}