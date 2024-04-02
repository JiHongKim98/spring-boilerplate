package com.example.demo.auth.application.dto;

import jakarta.validation.constraints.NotNull;

public record LogoutRequest(
	@NotNull String refreshToken
) {
}
