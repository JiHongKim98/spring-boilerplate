package com.example.oauth2.auth.infrastructure.google;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.google")
public record GoogleProperties(
	String clientId,
	String clientSecret,
	String redirectUri,
	String grantType
) {
}
