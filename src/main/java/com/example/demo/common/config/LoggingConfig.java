package com.example.demo.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.common.logging.LoggingInterceptor;
import com.example.demo.common.logging.MdcLoggingFilter;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class LoggingConfig implements WebMvcConfigurer {

	private final LoggingInterceptor loggingInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggingInterceptor)
			.addPathPatterns("/**")
			.order(0);  // 인증 interceptor 보다 우선 순위로 지정
	}

	@Bean
	public FilterRegistrationBean<Filter> filter1() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();

		registration.setFilter(new MdcLoggingFilter());
		registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		registration.addUrlPatterns("/*");

		return registration;
	}
}
