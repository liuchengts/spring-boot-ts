package com.search.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Configuration
@EnableAsync //启用异步
@EnableElasticsearchRepositories(basePackages = "com.search.core.repository")
@EntityScan({"com.search.common"})
@ComponentScan({"com.search"})
public class SearchServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchServerApplication.class, args);
	}
}
