package com.example.demo.member.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.demo.member.fixture.MemberFixture;

class MemberTest {

	@DisplayName("관리자 권한을 가진 회원인지 boolean 타입으로 반환한다.")
	@Test
	void memberRoleCheckTest() {
		// given
		Member normalMember = MemberFixture.normalMember();
		Member adminMember = MemberFixture.adminMember();

		// when
		boolean normalMemberResult = normalMember.isAdmin();
		boolean adminMemberResult = adminMember.isAdmin();

		// then
		assertSoftly(softly -> {
			softly.assertThat(normalMemberResult).isFalse();
			softly.assertThat(adminMemberResult).isTrue();
		});
	}

	@DisplayName("회원의 권한을 수정한다.")
	@Test
	void memberUpdateRoleTest() {
		// given
		Member normalMember = MemberFixture.normalMember();

		// when
		normalMember.updateRole(Role.ADMIN);

		// then
		assertThat(normalMember.getRole()).isEqualTo(Role.ADMIN);
	}

	@DisplayName("회원의 name 을 변경한다.")
	@ValueSource(strings = {"name-update", "hello"})
	@ParameterizedTest
	void memberUpdateNameTest(String newName) {
		// given
		Member member = MemberFixture.normalMember();

		// when
		member.updateName(newName);

		// then
		assertThat(member.getName()).isEqualTo(newName);
	}
}
