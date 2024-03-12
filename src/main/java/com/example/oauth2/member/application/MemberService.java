package com.example.oauth2.member.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.oauth2.member.application.dto.MyInfoResponse;
import com.example.oauth2.member.domain.Member;
import com.example.oauth2.member.domain.respository.MemberRepository;
import com.example.oauth2.member.exception.MemberException;
import com.example.oauth2.member.exception.MemberExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public MyInfoResponse getOwnInfo(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(MemberExceptionType.NOT_EXIST_MEMBER));

		return MyInfoResponse.of(member);
	}
}
