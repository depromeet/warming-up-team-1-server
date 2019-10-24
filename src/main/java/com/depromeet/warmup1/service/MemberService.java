package com.depromeet.warmup1.service;

import com.depromeet.warmup1.dto.LoginDto;
import com.depromeet.warmup1.entity.Member;
import java.util.List;

public interface MemberService {
	Member getMemberByMid(Long mid);
	LoginDto getOrCreateMember(String kakaoToken);
  String getJwtToken(String refreshToken);
	String createConnectKey(Long mid);
	List<Member> getCouple(String connectKey);
	Member connectMember(Long mid, String connectKey);
}