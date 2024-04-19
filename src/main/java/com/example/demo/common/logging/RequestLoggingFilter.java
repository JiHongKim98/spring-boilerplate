package com.example.demo.common.logging;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.example.demo.common.utils.StreamUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RequestLoggingFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {
		ContentCachingRequestWrapper cachedRequest = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper cachedResponse = new ContentCachingResponseWrapper(response);

		// Latency
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();

		filterChain.doFilter(cachedRequest, cachedResponse);

		stopWatch.stop();
		long totalTimeMillis = stopWatch.getTotalTimeMillis();
		logging(cachedRequest, cachedResponse, totalTimeMillis);

		cachedResponse.copyBodyToResponse();
	}

	private void logging(
		ContentCachingRequestWrapper request,
		ContentCachingResponseWrapper response,
		long totalTimeMillis
	) {
		// Request & Response info
		int status = response.getStatus();
		String method = request.getMethod();
		String requestURI = request.getRequestURI();
		String queryString = request.getQueryString();
		String statusCode = HttpStatus.valueOf(status).toString();

		// TODO: 리팩토링
		// Generated log message
		StringBuilder logMessage = new StringBuilder();

		logMessage.append("|\n| [REQUEST] (").append(method).append(") ").append(requestURI);

		getFormattedQueryString(queryString).ifPresent(v ->
			logMessage.append(v).append(queryString)
		);

		logMessage.append("\n| >> STATUS_CODE: ").append(statusCode);

		getRequestBody(request).ifPresent(v ->
			logMessage.append("\n| >> REQUEST_BODY: ").append(v)
		);

		getResponseBody(response).ifPresent(v ->
			logMessage.append("\n| >> RESPONSE_BODY: ").append(v)
		);

		logMessage.append("\n| >> TOTAL_LATENCY_TIME: ").append(totalTimeMillis).append("ms");

		if (status < 500) {
			log.info(logMessage.toString());
		} else {
			log.error(logMessage.toString());
		}
	}

	private Optional<String> getFormattedQueryString(String queryString) {
		if (StringUtils.hasText(queryString)) {
			return Optional.of("?");
		}
		return Optional.empty();
	}

	private Optional<String> getRequestBody(ContentCachingRequestWrapper request) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(request.getContentAsByteArray());
		String requestBody = StreamUtil.toString(inputStream);
		if (StringUtils.hasText(requestBody)) {
			return Optional.of(requestBody);
		}
		return Optional.empty();
	}

	private Optional<String> getResponseBody(ContentCachingResponseWrapper response) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(response.getContentAsByteArray());
		String responseBody = StreamUtil.toString(inputStream);
		if (StringUtils.hasText(responseBody)) {
			return Optional.of(responseBody);
		}
		return Optional.empty();
	}

	// convertInputStreamToString -> toString & utils 패키지로 이동
	// private String convertInputStreamToString(ByteArrayInputStream inputStream) {
	// 	try {
	// 		return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
	// 	} catch (IOException ex) {
	// 		log.error("convert exception", ex);
	// 		return "";
	// 	}
	// }
}
