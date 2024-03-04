package com.example.oauth2.auth.infrastructure.google.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GoogleToken(
	@JsonProperty("access_token") String accessToken
) {
}
