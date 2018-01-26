package com.account.cloud.starter.hystrix;

import com.account.cloud.starter.client.AccountClient;
import com.account.common.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 提供feign方式的ChatLogClient接口熔断
 */
@Slf4j
@Component
public class AccountHystrix implements AccountClient {


    @Override
    public Account findAccount(String accountName, String accountPwd) {
        log.error("熔断执行");
        return null;
    }

    @Override
    public Account findByAccountName(String accountName) {
        return null;
    }

    @Override
    public Account findById(Long id) {
        return null;
    }
}
