package com.example.oauth2.auth.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.oauth2.auth.presentation.support.AuthArgumentResolver;
import com.example.oauth2.auth.presentation.support.AuthInterceptor;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final AuthInterceptor authInterceptor;
	private final AuthArgumentResolver authArgumentResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(authArgumentResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor)
			.addPathPatterns("/api/v1/oauth/test");
	}
}
