package com.example.demo.common.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health-check")
public class HealthCheckController {

	@GetMapping
	public ResponseEntity<HealthCheckResponse> healthCheck() {
		return ResponseEntity.ok().body(HealthCheckResponse.of("ok"));
	}

	public record HealthCheckResponse(
		String message
	) {
		public static HealthCheckResponse of(String message) {
			return new HealthCheckResponse(message);
		}
	}
}
