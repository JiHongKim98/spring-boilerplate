package com.example.demo.auth.domain;

import java.util.UUID;

import com.example.demo.common.domain.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "refresh_token")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "member_id")
	private Long memberId;

	@Column(name = "token_id")
	private String tokenId;

	public Token(Long memberId) {
		this.memberId = memberId;
		this.tokenId = generatedTokenId();
	}

	public void renewTokenId() {
		this.tokenId = generatedTokenId();
	}

	private String generatedTokenId() {
		return UUID.randomUUID().toString();
	}
}
