package com.account.core.repository;


import com.account.common.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by apple on 2018/1/18.
 * db操作
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Serializable>
        , JpaSpecificationExecutor<Account> {
    /**
     * 查询账户信息
     *
     * @param accountName 账户名
     * @return 返回账户对象
     */
    Account findByAccountNameEquals(String accountName);

    /**
     * 查询账户信息
     *
     * @param accountName 账户名
     * @param accountPwd  账户密码
     * @return 返回账户对象
     */
    Account findByAccountNameEqualsAndAccountPwdEquals(String accountName, String accountPwd);
}
