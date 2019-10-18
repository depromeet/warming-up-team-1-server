package com.depromeet.warmup1.service;

import com.depromeet.warmup1.entity.Member;

public interface MemberService {
	public Member getMemberByMid(Long mid);
	public Member getOrCreateMember(String kakaoToken);
}
