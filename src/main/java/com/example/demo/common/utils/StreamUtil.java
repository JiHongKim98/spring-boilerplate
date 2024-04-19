package com.example.demo.common.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class StreamUtil {

	public static String toString(ByteArrayInputStream inputStream) {
		try {
			return org.springframework.util.StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
		} catch (IOException ex) {
			return "";
		}
	}
}
