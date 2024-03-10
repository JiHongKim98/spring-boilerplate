package com.example.oauth2.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.oauth2.member.application.dto.MyInfoResponse;
import com.example.oauth2.member.domain.Member;
import com.example.oauth2.member.domain.respository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public MyInfoResponse getOwnInfo(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new RuntimeException("404 NOT FOUND - MEMBER EXCEPTION"));  // TODO: 예외처리 보강

		return MyInfoResponse.of(member);
	}
}
