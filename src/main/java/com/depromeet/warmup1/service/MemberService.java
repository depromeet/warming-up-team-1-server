package com.depromeet.warmup1.service;

import java.util.List;

import com.depromeet.warmup1.entity.Member;

public interface MemberService {
	Member getMemberByMid(Long mid);
	Member getOrCreateMember(String kakaoToken);
	String createConnectKey(Long mid);
	List<Member> getCouple(String connectKey);
	Member connectMember(Long mid, String connectKey);
}
