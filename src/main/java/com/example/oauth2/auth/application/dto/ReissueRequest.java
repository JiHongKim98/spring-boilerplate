package com.example.oauth2.auth.application.dto;

import jakarta.validation.constraints.NotNull;

public record ReissueRequest(
	@NotNull String refreshToken
) {
}
