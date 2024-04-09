package com.example.demo.common.logging;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.common.logging.query.QueryCounter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {

	// TODO: 로깅 초안
	// [WEB]  REQUEST: (GET) "/api/v1/members", STATUS: 200, QUERY_COUNT: 2, FINISH_TIME: 10ms
	private static final String LOG_FORMAT =  // TODO: util 패키지로 빼기?
		"[WEB]  REQUEST: ({}) \"{}\", STATUS: {}, QUERY_COUNT: {}, FINISH_TIME: {}ms";

	private final QueryCounter queryCounter;

	@Override
	public void afterCompletion(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		Exception ex
	) throws Exception {
		log.info(
			LOG_FORMAT,
			request.getMethod(),
			request.getRequestURI(),
			response.getStatus(),
			queryCounter.getQueryCount(),
			99999999L  // TODO: 처리 완료 시간 구현
		);
	}
}
