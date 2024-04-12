package com.example.demo.common.logging;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.common.logging.latency.LatencyContext;
import com.example.demo.common.logging.query.QueryCounter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {

	// TODO: 로깅 초안?
	// [WEB]  REQUEST: (GET) "/api/v1/members", STATUS: 200, QUERY_COUNT: 2, FINISH_TIME: 10ms
	private static final String LOG_FORMAT =
		"[WEB]  ({}) \"{}\" | STATUS: {} | QUERY_COUNT: {} | FINISH_TIME: {}ms";

	private final QueryCounter queryCounter;
	private final LatencyContext latencyContext;

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler
	) throws Exception {
		latencyContext.setStartTime();
		return true;
	}

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
			latencyContext.getTotalTakenTime()
		);
	}
}
