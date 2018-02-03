package com.account.core.service.impl;

import com.account.common.Account;
import com.account.core.repository.AccountRepository;
import com.account.core.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 实现db的业务接口
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account findAccount(String accountName, String accountPwd) {
        return accountRepository.findByAccountNameEqualsAndAccountPwdEquals(accountName, accountPwd);
    }

    @Override
    public Account findByAccountName(String accountName) {
        return accountRepository.findByAccountNameEquals(accountName);
    }
}
