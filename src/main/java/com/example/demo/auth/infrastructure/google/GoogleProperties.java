package com.example.demo.auth.infrastructure.google;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.google")
public record GoogleProperties(
	String clientId,
	String clientSecret,
	String redirectUri,
	String grantType
) {
}
