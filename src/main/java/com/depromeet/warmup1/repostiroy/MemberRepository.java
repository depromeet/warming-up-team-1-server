package com.depromeet.warmup1.repostiroy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.depromeet.warmup1.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	public Member findOneByMid(Long mid);

	public Member findOneByName(String name);

	public Optional<Member> findOneByKakaoId(String id);
}