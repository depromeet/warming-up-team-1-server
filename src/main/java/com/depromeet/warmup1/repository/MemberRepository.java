package com.depromeet.warmup1.repository;

import com.depromeet.warmup1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findOneByMid(Long mid);

    Member findOneByName(String name);

    List<Member> findAllByConnectKey(String connectKey);

    Optional<Member> findOneByKakaoId(String id);
}