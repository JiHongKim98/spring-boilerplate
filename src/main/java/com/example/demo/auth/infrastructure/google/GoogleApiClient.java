package com.example.demo.auth.infrastructure.google;

import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.example.demo.auth.infrastructure.google.dto.GoogleToken;
import com.example.demo.auth.infrastructure.google.dto.GoogleUserInfo;

public interface GoogleApiClient {

	@PostExchange(url = "https://oauth2.googleapis.com/token")
	GoogleToken fetchToken(@RequestParam MultiValueMap<String, String> params);

	@GetExchange(url = "https://www.googleapis.com/oauth2/v3/userinfo")
	GoogleUserInfo fetchUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
}
