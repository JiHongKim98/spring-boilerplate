package com.example.oauth2.auth.domain;

public interface TokenProvider {

	String generated(AuthPayload authPayload);

	AuthPayload extract(String token);
}
