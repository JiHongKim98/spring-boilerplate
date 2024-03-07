package com.example.oauth2.auth.presentation.support;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

	private final AuthContext authContext;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		log.info("Call :: AuthArgumentResolver - supportsParameter()");

		return parameter.getParameterType().equals(Long.class) &&
			parameter.hasParameterAnnotation(Auth.class);
	}

	@Override
	public Object resolveArgument(
		MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory
	) throws Exception {
		log.info("Call :: AuthArgumentResolver - resolveArgument()");

		return authContext.getMemberId();
	}
}
