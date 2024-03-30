package com.example.demo.auth.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.application.AuthService;
import com.example.demo.auth.application.dto.ReissueRequest;
import com.example.demo.auth.application.dto.ReissueResponse;
import com.example.demo.auth.application.dto.TokenResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@GetMapping("/{socialType}/callback")
	public ResponseEntity<TokenResponse> loginOrRegister(
		@PathVariable("socialType") String socialType,
		@RequestParam("code") String code
	) {
		return ResponseEntity.ok(authService.loginOrRegister(socialType, code));
	}

	@GetMapping("/reissue")
	public ResponseEntity<ReissueResponse> reissueToken(
		@RequestBody @Valid ReissueRequest request
	) {
		return ResponseEntity.ok(authService.reissueToken(request.refreshToken()));
	}
}
