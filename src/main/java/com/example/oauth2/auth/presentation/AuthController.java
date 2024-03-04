package com.example.oauth2.auth.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth2.auth.application.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/oauth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	// TODO: 반환값 void -> JWT 토큰
	@GetMapping("/{socialType}/callback")
	public void loginOrCreateMember(
		@PathVariable("socialType") String socialType,
		@RequestParam("code") String code
	) {
		log.info("socialType = {}, code = {}", socialType, code);
		authService.loginOrCreateMember(socialType, code);
	}
}
