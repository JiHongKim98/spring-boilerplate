package com.example.demo.common.logging.query;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class QueryCountInterceptor implements HandlerInterceptor {

	private static final int WARN_QUERY_COUNT = 7;  // FIXME: 임시
	private static final String LOG_FORMAT = "|\n| QUERY_COUNT: {}";

	private final QueryCounter queryCounter;

	@Override
	public void afterCompletion(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		Exception ex
	) throws Exception {
		int queryCount = queryCounter.getQueryCount();

		if (queryCount < WARN_QUERY_COUNT) {
			log.info(LOG_FORMAT, queryCount);
		} else {
			log.warn(LOG_FORMAT, queryCount);
		}
	}
}
