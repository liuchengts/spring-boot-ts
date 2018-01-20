package com.account.core.service;

import com.account.common.Account;

/**
 * 需要做些业务本项目业务逻辑后再进行db处理的接口声明
 * 当直接调用Repository层无法完成时，就使用这一层
 */
public interface AccountService {

    /**
     * 查询账户信息
     * @param accountName 账户名
     * @param accountPwd 账户密码
     * @return 返回账户对象
     */
    Account findAccount(String accountName, String accountPwd);
    /**
     * 查询账户信息
     * @param accountName 账户名
     * @return 返回账户对象
     */
    Account findOneAccount(String accountName);

}
