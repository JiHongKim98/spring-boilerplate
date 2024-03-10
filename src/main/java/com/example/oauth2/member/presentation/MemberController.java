package com.example.oauth2.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.oauth2.auth.presentation.support.Auth;
import com.example.oauth2.member.application.MemberService;
import com.example.oauth2.member.application.dto.MyInfoResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/me")
	public ResponseEntity<MyInfoResponse> me(@Auth Long memberId) {
		log.info("memberId => {}", memberId);

		return ResponseEntity.ok(memberService.getOwnInfo(memberId));
	}
}
