package com.example.oauth2.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// TODO: JPA Auditing 추가
@Table(name = "member")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "social_type", nullable = false)
	private SocialType socialType;

	@Column(name = "social_id", unique = true, nullable = false)
	private String socialId;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String name;

	@Column(name = "image_uri")
	private String imageUri;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Builder
	public Member(SocialType socialType, String socialId, String email, String name, String imageUri, Role role) {
		this.socialType = socialType;
		this.socialId = socialId;
		this.name = name;
		this.email = email;
		this.imageUri = imageUri;
		this.role = role;
	}

	public boolean isAdmin() {
		return this.role == Role.ADMIN;
	}

	public void updateName(String name) {
		this.name = name;
	}

	public void updateRole(Role role) {
		this.role = role;
	}
}
