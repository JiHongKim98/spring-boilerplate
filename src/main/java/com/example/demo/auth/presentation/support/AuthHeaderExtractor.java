package com.example.demo.auth.presentation.support;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;

public class AuthHeaderExtractor {

	private static final String BEARER = "Bearer";

	public static Optional<String> extract(HttpServletRequest request) {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.hasText(header) && header.startsWith(BEARER)) {
			return Optional.of(header.substring(BEARER.length()).trim());
		}
		return Optional.empty();
	}
}
