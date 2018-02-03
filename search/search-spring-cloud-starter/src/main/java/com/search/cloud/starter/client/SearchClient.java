package com.search.cloud.starter.client;

import com.search.cloud.starter.hystrix.SearchHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "account-server", fallback = SearchHystrix.class)
public interface SearchClient {
}
