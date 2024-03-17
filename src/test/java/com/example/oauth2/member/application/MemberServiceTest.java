package com.example.oauth2.member.application;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.oauth2.member.application.dto.MyInfoResponse;
import com.example.oauth2.member.domain.Member;
import com.example.oauth2.member.domain.respository.MemberRepository;
import com.example.oauth2.member.exception.MemberException;
import com.example.oauth2.member.exception.MemberExceptionType;
import com.example.oauth2.member.fixture.MemberFixture;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@Mock
	MemberRepository memberRepository;

	@InjectMocks
	MemberService memberService;

	@DisplayName("해당 식별자에 해당하는 회원 정보를 반환.")
	@Test
	void getMemberInfoTest() {
		// given
		Member member = MemberFixture.normalMember();
		MyInfoResponse myInfoResponse = MyInfoResponse.of(member);
		given(memberRepository.findById(any()))
			.willReturn(Optional.of(member));

		// when
		MyInfoResponse actual = memberService.getOwnInfo(member.getId());

		// then
		Assertions.assertThat(actual).isEqualTo(myInfoResponse);
	}

	@DisplayName("존재하지 않는 회원이라면 예외를 반환.")
	@Test
	void throwExceptionWithNotExistMember() {
		// given
		given(memberRepository.findById(any()))
			.willReturn(Optional.empty());

		// when & then
		Assertions.assertThatThrownBy(() -> memberService.getOwnInfo(99999L))
			.isInstanceOf(MemberException.class)
			.hasMessage(MemberExceptionType.NOT_EXIST_MEMBER.message());
	}
}
