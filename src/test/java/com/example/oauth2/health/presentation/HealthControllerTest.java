package com.example.oauth2.health.presentation;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HealthController.class)
class HealthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void healthCheckTest() throws Exception {
		// given
		String response = "ok";

		// when & then
		mockMvc.perform(get("/api/v1/health-check"))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
	}

	@Test
	public void serverTimeCheckTest() throws Exception {
		// given
		// 2024-01-23T45:67:89.123456789
		Matcher<String> timeMatcher = matchesPattern("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{9}");

		// when & then
		mockMvc.perform(get("/api/v1/server-time-check"))
			.andExpect(status().isOk())
			.andExpect(content().string(timeMatcher));
	}
}
