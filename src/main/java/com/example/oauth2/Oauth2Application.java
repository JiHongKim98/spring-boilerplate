package com.example.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Oauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2Application.class, args);
	}

}
