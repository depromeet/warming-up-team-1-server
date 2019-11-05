package com.depromeet.warmup1.service.impl;

import com.depromeet.warmup1.adapter.KakaoAdapter;
import com.depromeet.warmup1.dto.KakaoUserDto;
import com.depromeet.warmup1.dto.LoginDto;
import com.depromeet.warmup1.entity.Connect;
import com.depromeet.warmup1.entity.Member;
import com.depromeet.warmup1.exception.ApiFailedException;
import com.depromeet.warmup1.exception.BadRequestException;
import com.depromeet.warmup1.exception.NotFoundException;
import com.depromeet.warmup1.repository.ConnectRepository;
import com.depromeet.warmup1.repository.MemberRepository;
import com.depromeet.warmup1.service.MemberService;
import com.depromeet.warmup1.util.UtilEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final JwtFactory jwtFactory;

    private final KakaoAdapter kakaoAdapter;

    private final MemberRepository memberRepository;

    private final UtilEncoder utilEncoder;

    private final ConnectRepository connectRepository;

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

        return LoginDto.from(jwtFactory.generateToken(member), jwtFactory.generateRefreshToken(member));

    }

    @Override
    @Transactional
    public String getJwtToken(String refreshToken) {
        Long id = jwtFactory.getMemberId(refreshToken).get();
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);

        return jwtFactory.generateToken(member);

    }

    @Override
    @Transactional
    public String createConnectKey(Long mid) {
        Member member = memberRepository.findOneByMid(mid);
        if (member == null)
            throw new NotFoundException();
        String target = member.getMid() + ":" + LocalDateTime.now().toString();
        member.setConnectKey(utilEncoder.encoding(target));
        //memberRepository.save(member);
        return member.getConnectKey();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> getCouple(String connectKey) {
        List<Member> members = memberRepository.findAllByConnectKey(connectKey);
        if(members == null)
        	throw new NotFoundException();
        return members;
    }

    @Override
    @Transactional
    public Member connectMember(Long mid, String connectKey) {
        List<Member> members = getCouple(connectKey);
        Member member = memberRepository.findOneByMid(mid);
        if (member == null)
        	throw new NotFoundException();
        if(member.getConnectKey()!=null)
        	throw new BadRequestException();

        member.setConnectKey(connectKey);
        members.add(member);
        Connect connect = Connect.builder().connectKey(connectKey)
                .build();
        connectRepository.save(connect);

        return memberRepository.save(member);
    }

}