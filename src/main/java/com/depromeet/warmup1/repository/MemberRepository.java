package com.depromeet.warmup1.repository;

import com.depromeet.warmup1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findOneByMid(Long mid);

    public Member findOneByName(String name);

    public List<Member> findAllByConnectKey(String connectKey);

    public Optional<Member> findOneByKakaoId(String id);
}