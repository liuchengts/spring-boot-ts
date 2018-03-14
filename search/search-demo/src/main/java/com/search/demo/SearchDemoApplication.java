package com.search.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Configuration
@EnableAsync //启用异步
public class SearchDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SearchDemoApplication.class, args);
	}
}
