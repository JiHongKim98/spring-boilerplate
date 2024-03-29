package com.example.oauth2.auth.domain;

public interface TokenExtractor {

	AuthPayload extract(String token);
}
