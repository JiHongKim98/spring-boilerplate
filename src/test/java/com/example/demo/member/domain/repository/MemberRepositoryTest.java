package com.example.demo.member.domain.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.member.domain.Member;
import com.example.demo.member.domain.respository.MemberRepository;
import com.example.demo.member.fixture.MemberFixture;

@DataJpaTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@DisplayName("소셜 아이디로 멤버를 찾는다")
	@Test
	void findMemberBySocialId() {
		// given
		Member member = MemberFixture.normalMember();
		memberRepository.save(member);

		// when
		Member actual = memberRepository.findBySocialId(member.getSocialId())
			.get();

		// then
		Assertions.assertThat(actual).isEqualTo(member);
	}
}
