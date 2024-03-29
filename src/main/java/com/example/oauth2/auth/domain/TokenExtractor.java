package com.example.oauth2.auth.domain;

public interface TokenExtractor {

	Long extract(String token);
}
