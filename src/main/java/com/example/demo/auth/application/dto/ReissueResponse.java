package com.example.demo.auth.application.dto;

public record ReissueResponse(
	String accessToken
) {

	public static ReissueResponse of(String accessToken) {
		return new ReissueResponse(accessToken);
	}
}
