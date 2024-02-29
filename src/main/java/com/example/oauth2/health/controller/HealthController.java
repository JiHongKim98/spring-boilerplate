package com.example.oauth2.health.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HealthController {

	@GetMapping("/health-check")
	public String healthCheck() {
		return "ok";
	}

	@GetMapping("/server-time-check")
	public String serverTimeCheck() {
		return LocalDateTime.now().toString();
	}
}
