package com.account.cloud.starter.client;


import com.account.cloud.starter.hystrix.AccountHystrix;
import com.account.common.Account;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 提供日志操作接口
 */
@FeignClient(name = "account-server", fallback = AccountHystrix.class)
public interface AccountClient {
    /**
     * 查询账户信息
     * @param accountName 账户名
     * @param accountPwd 账户密码
     * @return 返回账户对象
     */
    @PostMapping("/findAccount")
    Account findAccount(@RequestParam(value = "accountName") String accountName, @RequestParam(value = "accountPwd") String accountPwd);
    /**
     * 查询账户信息
     * @param accountName 账户名
     * @return 返回账户对象
     */
    @PostMapping("/findOneAccount")
    Account findOneAccount(@RequestParam(value = "accountName") String accountName);
}