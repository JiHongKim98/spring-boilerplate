package com.example.oauth2.auth.presentation.support;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.oauth2.auth.domain.AuthPayload;
import com.example.oauth2.auth.domain.TokenProvider;
import com.example.oauth2.auth.exception.AuthException;
import com.example.oauth2.auth.exception.AuthExceptionType;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

	private final TokenProvider tokenProvider;
	private final AuthContext authContext;

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler
	) throws Exception {
		log.info("Call :: LoginInterceptor - preHandle()");

		String token = AuthHeaderExtractor.extract(request)
			.orElseThrow(() -> new AuthException(AuthExceptionType.UNAUTHORIZED));
		AuthPayload authPayload = tokenProvider.extract(token);
		authContext.setMemberId(authPayload.memberId());
		return true;
	}
}
