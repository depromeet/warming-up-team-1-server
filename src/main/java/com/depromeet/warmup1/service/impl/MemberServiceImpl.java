package com.depromeet.warmup1.service.impl;

import com.depromeet.warmup1.adapter.KakaoAdapter;
import com.depromeet.warmup1.dto.KakaoUserDto;
import com.depromeet.warmup1.dto.LoginDto;
import com.depromeet.warmup1.entity.Member;
import com.depromeet.warmup1.exception.ApiFailedException;
import com.depromeet.warmup1.exception.NotFoundException;
import com.depromeet.warmup1.repostiroy.MemberRepository;
import com.depromeet.warmup1.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final JwtFactory jwtFactory;
    @Autowired
    private KakaoAdapter kakaoAdapter;
    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public Member getMemberByMid(Long mid) {
        return memberRepository.findById(mid)
                .orElseThrow(() -> new ApiFailedException("Member is not found.", HttpStatus.NOT_FOUND));
    }

    /**
     * login
     *
     * @param accessToken
     * @return Member
     */
    @Override
    @Transactional
    public LoginDto getOrCreateMember(String kakaoToken) {
        final KakaoUserDto kakaoUserDto = kakaoAdapter.getUserInfo(kakaoToken);
        if (kakaoUserDto == null) {
            throw new ApiFailedException("Failed to get user info from kakao api", HttpStatus.SERVICE_UNAVAILABLE);
        }
        final String kakaoId = kakaoUserDto.getId().toString();
        final String profileImgUrl = kakaoUserDto.getProfileImage();
        final String name = kakaoUserDto.getUserName();
        Member member = memberRepository.findOneByKakaoId(kakaoId).orElseGet(() -> {
            final Member m = new Member();
            m.setKakaoId(kakaoId);
            m.setProfileImgUrl(profileImgUrl);
            m.setName(name);
            return memberRepository.save(m);
        });


        return LoginDto.from(
                jwtFactory.generateToken(member),
                jwtFactory.generateRefreshToken(member)
        );

    }

    @Override
    public String getJwtToken(String refreshToken) {
        Long id = jwtFactory.getMemberId(refreshToken).get();
        Member member = memberRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        return jwtFactory.generateToken(member);

    }

}