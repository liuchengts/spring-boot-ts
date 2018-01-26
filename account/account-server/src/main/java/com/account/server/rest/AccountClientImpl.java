package com.account.server.rest;


import com.account.cloud.starter.client.AccountClient;
import com.account.common.Account;
import com.account.core.repository.AccountRepository;
import com.account.core.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AccountClientImpl implements AccountClient {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findAccount(@RequestParam(value = "accountName") String accountName,
                               @RequestParam(value = "accountPwd") String accountPwd) {
        return accountService.findAccount(accountName, accountPwd);
    }

    @Override
    public Account findByAccountName(@RequestParam(value = "accountName") String accountName) {
        return accountService.findByAccountName(accountName);
    }

    @Override
    public Account findById(@RequestParam(value = "id") Long id) {
        return accountRepository.findOne(id);
    }
}
