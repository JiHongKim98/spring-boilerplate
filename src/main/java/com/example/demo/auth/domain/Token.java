package com.example.demo.auth.domain;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

	private String tokenId;

	private Long memberId;

	public Token(Long memberId) {
		this.tokenId = generatedTokenId();
		this.memberId = memberId;
	}

	private String generatedTokenId() {
		return UUID.randomUUID().toString();
	}
}
