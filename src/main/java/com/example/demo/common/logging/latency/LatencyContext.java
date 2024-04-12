package com.example.demo.common.logging.latency;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LatencyContext {

	private Long startTime;

	public void setStartTime() {
		startTime = System.currentTimeMillis();
	}

	public Long getTotalTakenTime() {
		return System.currentTimeMillis() - startTime;
	}
}
