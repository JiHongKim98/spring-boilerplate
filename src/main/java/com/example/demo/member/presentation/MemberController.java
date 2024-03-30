package com.example.demo.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.annotation.Auth;
import com.example.demo.member.application.MemberService;
import com.example.demo.member.application.dto.MyInfoResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/me")
	public ResponseEntity<MyInfoResponse> me(@Auth Long memberId) {
		return ResponseEntity.ok(memberService.getOwnInfo(memberId));
	}
}
