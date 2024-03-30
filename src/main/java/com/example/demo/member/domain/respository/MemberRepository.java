package com.example.demo.member.domain.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	Optional<Member> findBySocialId(String socialId);
}
