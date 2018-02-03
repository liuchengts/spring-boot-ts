package com.search.cloud.starter.hystrix;

import com.search.cloud.starter.client.SearchClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 提供feign方式的接口熔断
 */
@Slf4j
@Component
public class SearchHystrix implements SearchClient {

}
