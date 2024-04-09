package com.example.demo.common.logging.query;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Getter;

@Getter
@Component
@RequestScope
public class QueryCounter {

	private int queryCount;

	public void increase() {
		queryCount++;
	}
}
