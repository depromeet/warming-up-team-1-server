package com.depromeet.warmup1.service;

import com.depromeet.warmup1.dto.LoginDto;
import com.depromeet.warmup1.entity.Member;

public interface MemberService {
    Member getMemberByMid(Long mid);

    LoginDto getOrCreateMember(String kakaoToken);

    String getJwtToken(String refreshToken);
}
