package com.example.oauth2.auth.domain;

public interface TokenProvider {

	String generated(Long memberId);
}
