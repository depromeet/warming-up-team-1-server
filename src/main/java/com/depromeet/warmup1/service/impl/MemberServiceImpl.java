package com.depromeet.warmup1.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.warmup1.adapter.KakaoAdapter;
import com.depromeet.warmup1.dto.KakaoUserDto;
import com.depromeet.warmup1.entity.Member;
import com.depromeet.warmup1.exception.ApiFailedException;
import com.depromeet.warmup1.repostiroy.MemberRepository;
import com.depromeet.warmup1.service.MemberService;
import com.depromeet.warmup1.util.UtilEncoder;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private KakaoAdapter kakaoAdapter;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private UtilEncoder utilEncoder;

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
	public Member getOrCreateMember(String kakaoToken) {
		final KakaoUserDto kakaoUserDto = kakaoAdapter.getUserInfo(kakaoToken);
		if (kakaoUserDto == null) {
			throw new ApiFailedException("Failed to get user info from kakao api", HttpStatus.SERVICE_UNAVAILABLE);
		}
		final String kakaoId = kakaoUserDto.getId().toString();
		final String profileImgUrl = kakaoUserDto.getProfileImage();
		final String name = kakaoUserDto.getUserName();
		return memberRepository.findOneByKakaoId(kakaoId).orElseGet(() -> {
			final Member member = new Member();
			member.setKakaoId(kakaoId);
			member.setProfileImgUrl(profileImgUrl);
			member.setName(name);
			return memberRepository.save(member);
		});
	}

	@Override
	@Transactional
	public String createConnectKey(Long mid) {
		Member member = memberRepository.findOneByMid(mid);
		if (member == null)
			return null;
		String target = member.getMid() + ":" + LocalDateTime.now().toString();
		member.setConnectKey(utilEncoder.encoding(target));
		memberRepository.save(member);
		return member.getConnectKey();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Member> getCouple(String connectKey) {
		return memberRepository.findAllByConnectKey(connectKey);
	}

	@Override
	@Transactional
	public Member connectMember(Long mid, String connectKey) {
		List<Member> members = getCouple(connectKey);
		Member member = memberRepository.findOneByMid(mid);
		if (member == null)
			return null;
		if (members.size() > 2 | members.size() == 0)
			return null;
		member.setConnectKey(connectKey);
		return memberRepository.save(member);
	}
}