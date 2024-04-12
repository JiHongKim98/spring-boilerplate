package com.example.demo.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	private final String[] allowedOrigins;
	private final String[] allowedMethods;

	public CorsConfig(
		@Value("${spring.cors.allowed-origins}") String[] allowedOrigins,
		@Value("${spring.cors.allowed-methods}") String[] allowedMethods
	) {
		this.allowedOrigins = allowedOrigins;
		this.allowedMethods = allowedMethods;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(allowedOrigins)
			.allowedMethods(allowedMethods)
			.allowCredentials(true)
			.exposedHeaders("*");
	}
}
